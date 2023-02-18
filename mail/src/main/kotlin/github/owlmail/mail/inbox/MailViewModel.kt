package github.owlmail.mail.inbox

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import github.owlmail.mail.MailRepository
import github.owlmail.mail.inbox.database.MailDAO
import github.owlmail.mail.inbox.model.InboxSearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class MailViewModel @Inject constructor(
    private val repository: MailRepository,
    private val mailDAO: MailDAO
) : ViewModel() {
    private val searchQuery = MutableStateFlow("")
    private val pagingConfig = PagingConfig(pageSize = 10, 10, false, 10)
    fun getPaginatedData(mailFolder: String = "inbox"): Flow<PagingData<InboxSearchResponse.Body.SearchResponse.Conversation>> {
        //paging source or paging lib implementation
        return searchQuery.flatMapLatest {

            Pager(pagingConfig, 0) {
                MailPagingSource(repository, mailFolder, it, mailDAO)
            }.flow
        }
    }

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }
}