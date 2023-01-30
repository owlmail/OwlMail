package github.owlmail.mail.inbox

import androidx.paging.PagingSource
import androidx.paging.PagingState
import github.owlmail.mail.MailRepository

class MailPagingSource(private val repository: MailRepository, private val mailFolder: String) :
    PagingSource<Int, InboxSearchResponse.Body.SearchResponse.Conversation>() {

    //call getMailList from repo
    override fun getRefreshKey(state: PagingState<Int, InboxSearchResponse.Body.SearchResponse.Conversation>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, InboxSearchResponse.Body.SearchResponse.Conversation> {
        try {
            val offset = params.key ?: 0
            val loadSize = params.loadSize
            val inboxSearchRequest = InboxSearchRequest(
                body = InboxSearchRequest.Body(
                    searchRequest = InboxSearchRequest.Body.SearchRequest(
                        jsns = "urn:zimbraMail",
                        limit = loadSize,
                        offset = offset,
                        query = "in:$mailFolder"
                    )
                )
            )
            val response = repository.getMailList(inboxSearchRequest)
            return LoadResult.Page(
                data = response.body?.searchResponse?.conversation?.filterNotNull() ?: emptyList(),
                prevKey = null,
                nextKey = if (response.body?.searchResponse?.more == true) {
                    offset + 1
                } else null
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}