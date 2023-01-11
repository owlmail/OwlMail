package github.owlmail.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel(
    private val repository: AuthRepository
) {
    private val _loginState = MutableStateFlow(false)
    val loginState = _loginState.asStateFlow()

    //logic for login function
    //flow or livedata to observe login state
    fun userLogin(userDetails : UserDetails){
//        viewModelScope.launch(Dispatchers.IO){
//            repository.userLogin(userDetails)
        //validate if success
//        }
    }

}