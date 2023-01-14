package github.owlmail.mail

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MailViewModel @Inject constructor(
    private val repository: MailRepository
) : ViewModel() {
    private val pagingConfig = PagingConfig(pageSize = 10, 10, false, 10)
    fun getPaginatedData(): Flow<PagingData<InboxSearchResponse.Body.SearchResponse.Conversation>> {
        //paging source or paging lib implementation
            return Pager(pagingConfig, 0) {
                MailPagingSource(repository)
            }.flow
    }
}