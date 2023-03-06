package github.owlmail.auth

import android.accounts.Account
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RequestAuth(
    @Json(name = "Body")
    val body: Body? = null
) {
    @JsonClass(generateAdapter = true)
    data class Body(
        @Json(name = "AuthRequest")
        val authRequest: AuthRequest? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class AuthRequest(
            @Json(name = "account")
            val account: Account? = null,
            @Json(name = "_jsns")
            val jsns: String? = null,
            @Json(name = "password")
            val password: Password? = null,
            @Json(name = "csrfTokenSecured")
            val csrfTokenSecured: Int? = null
        ) {
            @JsonClass(generateAdapter = true)
            data class Account(
                @Json(name = "_content")
                val content: String? = null
            )

            @JsonClass(generateAdapter = true)
            data class Password(
                @Json(name = "_content")
                val content: String? = null
            )
        }
    }
}
