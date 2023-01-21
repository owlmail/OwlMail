package github.owlmail.auth

import github.owlmail.networking.AuthIntercepter

class AuthRepository(
    private val authIntercepter: AuthIntercepter,
    private val service: AuthService
) {
    //network call for login via retrofit service
    suspend fun userLogin(requestAuth: RequestAuth) = service.makeAuthRequest(requestAuth)

    fun saveAuthTokens(csrfToken: String,cookieToken: String){
        authIntercepter.csrfToken = csrfToken
        authIntercepter.cookie = cookieToken

    }
}