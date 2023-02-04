package github.owlmail.auth

data class UserDetails(val userId: String, val userPassword: String) {
    fun mapToRequestAuth(): RequestAuth {
        return RequestAuth(
            RequestAuth.Body(
                RequestAuth.Body.AuthRequest(
                    account = RequestAuth.Body.AuthRequest.Account(
                        userId
                    ),
                    password = RequestAuth.Body.AuthRequest.Password(
                        userPassword
                    ),
                    jsns = "urn:zimbraAccount",
                    csrfTokenSecured = 1
                )
            )
        )
    }
}
