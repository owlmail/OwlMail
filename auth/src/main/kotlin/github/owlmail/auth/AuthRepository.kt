package github.owlmail.auth

import github.owlmail.networking.AuthInterceptor

class AuthRepository(
    private val authInterceptor: AuthInterceptor,
    private val service: AuthService
) {
    // network call for login via retrofit service
    suspend fun userLogin(requestAuth: RequestAuth) = service.makeAuthRequest(requestAuth)

    fun saveAuthTokens(csrfToken: String, cookieToken: String) {
        authInterceptor.csrfToken = csrfToken
        authInterceptor.cookie = cookieToken
    }
}
