package github.owlmail.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.owlmail.networking.ResponseState
import github.owlmail.networking.mapToResponseState
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val repository: AuthRepository
) : ViewModel() {
    private var userDetails: UserDetails? = null
    private val _loginState = MutableStateFlow<ResponseState<ResponseAuth?>>(ResponseState.Empty)
    val loginState = _loginState.asStateFlow()

    // logic for login function
    // flow or livedata to observe login state
    fun userLogin(userDetails: UserDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            this@AuthViewModel.userDetails = userDetails
            _loginState.value =
                repository.userLogin(userDetails.mapToRequestAuth()).mapToResponseState()
            // validate if success
        }
    }

    fun saveUserDetails() = viewModelScope.launch {
        userDetails?.let {
            dataStoreManager.saveToDataStore(it)
        }
    }

    fun readUserDetails() = dataStoreManager.readFromDataStore()

    fun saveAuthTokens(csrfToken: String, cookieToken: String) {
        repository.saveAuthTokens(csrfToken = csrfToken, cookieToken = cookieToken)
    }
}
