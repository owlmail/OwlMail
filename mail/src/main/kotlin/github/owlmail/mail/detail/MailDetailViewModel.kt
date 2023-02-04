package github.owlmail.mail.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.owlmail.mail.MailRepository
import github.owlmail.mail.detail.model.ConvDetails
import github.owlmail.mail.detail.model.MailDetailResponse
import github.owlmail.networking.ResponseState
import github.owlmail.networking.mapToResponseState
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MailDetailViewModel @Inject constructor(
    private val repository: MailRepository,
    private val detailDAO: DetailDAO
) : ViewModel() {
    private val _mailDetail =
        MutableStateFlow<ResponseState<MailDetailResponse?>>(ResponseState.Empty)
    val mailDetail = _mailDetail.asStateFlow()
    fun getMailDetail(convDetails: ConvDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            val result =
                repository.getMailDetail(convDetails.mapToDetailRequest()).mapToResponseState()
            _mailDetail.value = result
            if (result is ResponseState.Success) {
                val list = result.data?.body?.searchConvResponse?.message?.filterNotNull().orEmpty()
                detailDAO.insertAllMessage(list)
            }
        }
    }
}
