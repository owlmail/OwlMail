package github.owlmail.auth

import github.owlmail.auth.api.AuthState
import github.owlmail.auth.api.AuthUseCase
import github.owlmail.networking.ResponseState
import github.owlmail.networking.mapToResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

class AuthUseCaseImpl(
    private val repository: AuthRepository,
    private val dataStoreManager: DataStoreManager
) : AuthUseCase {

    private val loginState = MutableStateFlow<AuthState>(AuthState.UNKNOWN)

    //
    override suspend fun invoke(userId: String, userPassword: String) {

        val userDetails = UserDetails(userId, userPassword)
        val response = repository.userLogin(userDetails.mapToRequestAuth()).mapToResponseState()

        when (response) {
            is ResponseState.Success -> {

                val csrfToken = response.data?.body?.authResponse?.csrfToken?.content ?: ""
                val cookieToken =
                    response.data?.body?.authResponse?.authToken?.firstOrNull()?.content ?: ""

                repository.saveAuthTokens(csrfToken = csrfToken, cookieToken = cookieToken)

                dataStoreManager.saveToDataStore(userDetails)

                loginState.value = AuthState.AUTHENTICATED

            }
            is ResponseState.Error -> {

                loginState.value = AuthState.NON_AUTHENTICATED

            }
            is ResponseState.Empty -> {

                loginState.value = AuthState.NON_AUTHENTICATED

            }
        }
    }

    override suspend fun invoke() {
        val preferences = dataStoreManager.readFromDataStore().first()
        val userid = preferences[DataStoreManager.userId]
        val password = preferences[DataStoreManager.password]
        if (userid.isNullOrEmpty() || password.isNullOrEmpty()) {
            loginState.value = AuthState.NON_AUTHENTICATED

        } else {
            invoke(userid,password)
        }
    }

    override fun get(): Flow<AuthState> {
        return loginState
    }
}