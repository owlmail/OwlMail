package github.owlmail.contacts

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import github.owlmail.contacts.model.ContactResponse
import github.owlmail.networking.NetworkStateFlowBuilder
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val repository: ContactRepository,
    private val contactDAO: ContactDAO,
    private val networkStateFlowBuilder: NetworkStateFlowBuilder
) : ViewModel() {
    private val searchQuery = MutableStateFlow("")
    private val pagingConfig = PagingConfig(pageSize = 10, 10, false, 10)
    fun getPaginatedData(): Flow<PagingData<ContactResponse.Body.SearchGalResponse.Cn>> {
        return searchQuery.flatMapLatest { query ->
            networkStateFlowBuilder().flatMapLatest { networkState ->
                Pager(pagingConfig, 0) {
                    ContactPagingSource(repository, query, contactDAO, networkState)
                }.flow
            }
        }
    }

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }
}
