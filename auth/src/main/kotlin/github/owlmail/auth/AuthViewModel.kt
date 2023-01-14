package github.owlmail.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _loginState = MutableStateFlow<ResponseAuth?>(null)
    val loginState = _loginState.asStateFlow()

    //logic for login function
    //flow or livedata to observe login state
    fun userLogin(userDetails: UserDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            _loginState.value =
            repository.userLogin(userDetails.mapToRequestAuth())
            //validate if success

        }
    }

}