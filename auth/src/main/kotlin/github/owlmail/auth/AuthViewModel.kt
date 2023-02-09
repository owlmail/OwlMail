package github.owlmail.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.owlmail.auth.api.AuthUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    val loginState = authUseCase.get()

    //logic for login function
    //flow or livedata to observe login state
    fun userLogin(userDetails: UserDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            authUseCase.invoke(userDetails.userId, userDetails.userPassword)
        }
    }

    fun tryAuthFromLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            authUseCase.invoke()
        }
    }

    init {
        tryAuthFromLocal()
    }
}