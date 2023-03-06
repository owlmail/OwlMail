package github.owlmail.mail.inbox

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import github.owlmail.mail.MailRepository
import github.owlmail.mail.inbox.database.MailDAO
import github.owlmail.mail.inbox.model.InboxSearchRequest
import github.owlmail.mail.inbox.model.InboxSearchResponse
import github.owlmail.networking.NetworkState
import github.owlmail.networking.ResponseState
import github.owlmail.networking.mapToResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MailPagingSource(
    private val repository: MailRepository,
    private val mailFolder: String,
    private val query: String,
    private val mailDAO: MailDAO,
    private val networkState: NetworkState
) :
    PagingSource<Int, InboxSearchResponse.Body.SearchResponse.Conversation>() {

    // call getMailList from repo
    override fun getRefreshKey(
        state: PagingState<Int, InboxSearchResponse.Body.SearchResponse.Conversation>
    ): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, InboxSearchResponse.Body.SearchResponse.Conversation> =
        withContext(Dispatchers.IO) {
            val offset = params.key ?: 0
            val loadSize = params.loadSize
            if (networkState == NetworkState.Unavailable) {
                val dbList = mailDAO.getAllMails(loadSize, offset * loadSize, "\"l\":\"2\"", query)
                Log.e("Preeti", " $offset $dbList")
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

            val inboxSearchRequest = InboxSearchRequest(
                body = InboxSearchRequest.Body(
                    searchRequest = InboxSearchRequest.Body.SearchRequest(
                        jsns = "urn:zimbraMail",
                        limit = loadSize,
                        offset = offset,
                        query = "$query in:$mailFolder".trim()
                    )
                )
            )
            when (val response = repository.getMailList(inboxSearchRequest).mapToResponseState()) {
                is ResponseState.Success -> {
                    val mailList =
                        response.data?.body?.searchResponse?.conversation?.filterNotNull().orEmpty()
                    mailDAO.insertAllMails(mailList)
                    LoadResult.Page(
                        data = mailList,
                        prevKey = null,
                        nextKey = if (response.data?.body?.searchResponse?.more == true) {
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
