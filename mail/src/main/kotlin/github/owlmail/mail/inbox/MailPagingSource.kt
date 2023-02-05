package github.owlmail.mail.inbox

import androidx.paging.PagingSource
import androidx.paging.PagingState
import github.owlmail.mail.MailRepository
import github.owlmail.mail.inbox.database.MailDAO
import github.owlmail.mail.inbox.model.InboxSearchRequest
import github.owlmail.mail.inbox.model.InboxSearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MailPagingSource(
    private val repository: MailRepository,
    private val mailFolder: String,
    private val query: String,
    private val mailDAO: MailDAO
) :
    PagingSource<Int, InboxSearchResponse.Body.SearchResponse.Conversation>() {

    //call getMailList from repo
    override fun getRefreshKey(state: PagingState<Int, InboxSearchResponse.Body.SearchResponse.Conversation>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, InboxSearchResponse.Body.SearchResponse.Conversation> =
        withContext(Dispatchers.Main) {
            try {
                val offset = params.key ?: 0
                val loadSize = params.loadSize
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
                val response = repository.getMailList(inboxSearchRequest)
                val mailList =
                    response.body?.searchResponse?.conversation?.filterNotNull().orEmpty()
                mailDAO.insertAllMails(mailList)
                return@withContext LoadResult.Page(
                    data = mailList,
                    prevKey = null,
                    nextKey = if (response.body?.searchResponse?.more == true) {
                        offset + 1
                    } else null
                )
            } catch (e: Exception) {
                return@withContext LoadResult.Error(e)
            }
        }
}