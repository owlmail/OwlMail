package github.owlmail.auth

class AuthRepository(
    private val service: AuthService
) {
    //network call for login via retrofit service
    suspend fun userLogin(requestAuth: RequestAuth) = service.makeAuthRequest(requestAuth)

}