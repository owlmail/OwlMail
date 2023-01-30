package github.owlmail.contacts

import androidx.paging.PagingSource
import androidx.paging.PagingState
import github.owlmail.contacts.model.ContactRequest
import github.owlmail.contacts.model.ContactResponse

class ContactPagingSource(
    private val repository: ContactRepository,
) :
    PagingSource<Int, ContactResponse.Body.SearchGalResponse.Cn>() {
    //call getMailList from repo
    override fun getRefreshKey(state: PagingState<Int, ContactResponse.Body.SearchGalResponse.Cn>): Int? {
        return null
    }

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, ContactResponse.Body.SearchGalResponse.Cn> {
        try {
            val offset = params.key ?: 0
            val loadSize = params.loadSize
            val contactRequest = ContactRequest(
                body = ContactRequest.Body(
                    searchGalRequest = ContactRequest.Body.SearchGalRequest(
                        jsns = "urn:zimbraMail",
                        limit = loadSize,
                        offset = offset,
                        name = ""
                    )
                )
            )
            val response = repository.getContactList(contactRequest)
            return PagingSource.LoadResult.Page(
                data = response.body?.searchGalResponse?.cn?.filterNotNull().orEmpty(),
                prevKey = null,
                nextKey = if (response.body?.searchGalResponse?.more == true) {
                    offset + 1
                } else null
            )
        } catch (e: Exception) {
            return PagingSource.LoadResult.Error(e)
        }
    }
}