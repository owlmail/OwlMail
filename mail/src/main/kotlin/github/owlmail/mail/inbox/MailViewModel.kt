package github.owlmail.mail.inbox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import github.owlmail.mail.MailRepository
import github.owlmail.mail.inbox.InboxSearchResponse
import github.owlmail.mail.inbox.MailPagingSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MailViewModel @Inject constructor(
    private val repository: MailRepository
) : ViewModel() {
    private val searchQuery = MutableStateFlow("")
    private val pagingConfig = PagingConfig(pageSize = 10, 10, false, 10)
    fun getPaginatedData(mailFolder: String = "inbox"): Flow<PagingData<InboxSearchResponse.Body.SearchResponse.Conversation>> {
        //paging source or paging lib implementation
            return searchQuery.flatMapLatest {

                Pager(pagingConfig, 0) {
                    MailPagingSource(repository, mailFolder, it)
                }.flow
            }
    }

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }
}