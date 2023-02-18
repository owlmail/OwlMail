package github.owlmail.contacts

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import github.owlmail.contacts.model.ContactResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val repository: ContactRepository,
    private val contactDAO: ContactDAO
) : ViewModel() {
    private val searchQuery = MutableStateFlow("")
    private val pagingConfig = PagingConfig(pageSize = 10, 10, false, 10)
    fun getPaginatedData(context: Context): Flow<PagingData<ContactResponse.Body.SearchGalResponse.Cn>> {
        return searchQuery.flatMapLatest {
            Pager(pagingConfig, 0) {

                ContactPagingSource(repository, it, contactDAO, context)
            }.flow
        }
    }
    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }
}