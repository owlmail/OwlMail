package github.owlmail.mail.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.owlmail.mail.MailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MailDetailViewModel @Inject constructor(
    private val repository: MailRepository
) : ViewModel() {
    private val _mailDetail = MutableStateFlow<MailDetailResponse?>(null)
    val mailDetail = _mailDetail.asStateFlow()
    fun getMailDetail(convDetails: ConvDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            _mailDetail.value = repository.getMailDetail(convDetails.mapToDetailRequest())
        }
    }
}