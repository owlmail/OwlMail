package github.owlmail.mail.inbox

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import github.owlmail.mail.MailRepository
import github.owlmail.mail.inbox.database.MailDAO
import github.owlmail.mail.inbox.model.InboxSearchResponse
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class MailViewModel @Inject constructor(
    private val repository: MailRepository,
    private val mailDAO: MailDAO
) : ViewModel() {
    private val searchQuery = MutableStateFlow("")
    private val pagingConfig = PagingConfig(pageSize = 10, 10, false, 10)
    fun getPaginatedData(mailFolder: String = "inbox"): LiveData<PagingData<InboxSearchResponse.Body.SearchResponse.Conversation>> {
        // paging source or paging lib implementation
        return searchQuery.asLiveData().switchMap {
            Pager(pagingConfig, 0) {
                MailPagingSource(repository, mailFolder, it, mailDAO)
            }.liveData
        }
    }

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }
}
