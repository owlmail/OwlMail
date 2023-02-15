package github.owlmail.contacts

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import github.owlmail.contacts.model.ContactResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val repository: ContactRepository,
    private val contactDAO: ContactDAO
) : ViewModel() {
    private val pagingConfig = PagingConfig(pageSize = 10, 10, false, 10)
    fun getPaginatedData(searchContact: String, context: Context): Flow<PagingData<ContactResponse.Body.SearchGalResponse.Cn>> {
        return Pager(pagingConfig, 0) {

            ContactPagingSource(repository, searchContact, contactDAO, context)
        }.flow
    }
}