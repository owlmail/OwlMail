package github.owlmail.contacts

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import github.owlmail.contacts.model.ContactRequest
import github.owlmail.contacts.model.ContactResponse
import github.owlmail.networking.NetworkState
import github.owlmail.networking.ResponseState
import github.owlmail.networking.mapToResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactPagingSource(
    private val repository: ContactRepository,
    private val searchContact: String,
    private val contactDAO: ContactDAO,
    private val networkState: NetworkState
) :
    PagingSource<Int, ContactResponse.Body.SearchGalResponse.Cn>() {

    override fun getRefreshKey(state: PagingState<Int, ContactResponse.Body.SearchGalResponse.Cn>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ContactResponse.Body.SearchGalResponse.Cn> =
        withContext(Dispatchers.IO) {
            val offset = params.key ?: 0
            val loadSize = params.loadSize
            if (networkState == NetworkState.Unavailable) {
                val dbList = contactDAO.getAllContacts(loadSize, offset * loadSize, searchContact)
                Log.e("Preeti", "$dbList")

                return@withContext LoadResult.Page(
                    data = dbList,
                    prevKey = null,
                    nextKey = if (dbList.size < loadSize) {
                        null
                    } else {
                        offset + 1
                    }
                )
            }
            val contactRequest = ContactRequest(
                body = ContactRequest.Body(
                    searchGalRequest = ContactRequest.Body.SearchGalRequest(
                        jsns = "urn:zimbraAccount",
                        limit = loadSize,
                        offset = offset,
                        name = "$searchContact"
                    )
                )
            )
            when (val response = repository.getContactList(contactRequest).mapToResponseState()) {
                is ResponseState.Success -> {
                    val contactList =
                        response.data?.body?.searchGalResponse?.cn?.filterNotNull().orEmpty()
                    contactDAO.insertAllContacts(contactList)
                    LoadResult.Page(
                        data = contactList,
                        prevKey = null,
                        nextKey = if (response.data?.body?.searchGalResponse?.more == true) {
                            offset + 1
                        } else {
                            null
                        }
                    )
                }
                else -> {
                    LoadResult.Error(Throwable(response.message))
                }
            }
        }
}
