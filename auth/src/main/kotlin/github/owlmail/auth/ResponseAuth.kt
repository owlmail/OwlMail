package github.owlmail.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseAuth(
    @Json(name = "Body")
    val body: Body? = null,
    @Json(name = "Header")
    val header: Header? = null,
    @Json(name = "_jsns")
    val jsns: String? = null
) {
    @JsonClass(generateAdapter = true)
    data class Body(
        @Json(name = "AuthResponse")
        val authResponse: AuthResponse? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class AuthResponse(
            @Json(name = "authToken")
            val authToken: List<AuthToken?>? = null,
            @Json(name = "csrfToken")
            val csrfToken: CsrfToken? = null,
            @Json(name = "_jsns")
            val jsns: String? = null,
            @Json(name = "lifetime")
            val lifetime: Int? = null,
            @Json(name = "skin")
            val skin: List<Skin?>? = null
        ) {
            @JsonClass(generateAdapter = true)
            data class AuthToken(
                @Json(name = "_content")
                val content: String? = null
            )

            @JsonClass(generateAdapter = true)
            data class CsrfToken(
                @Json(name = "_content")
                val content: String? = null
            )

            @JsonClass(generateAdapter = true)
            data class Skin(
                @Json(name = "_content")
                val content: String? = null
            )
        }
    }

    @JsonClass(generateAdapter = true)
    data class Header(
        @Json(name = "context")
        val context: Context? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class Context(
            @Json(name = "change")
            val change: Change? = null,
            @Json(name = "_jsns")
            val jsns: String? = null
        ) {
            @JsonClass(generateAdapter = true)
            data class Change(
                @Json(name = "token")
                val token: Int? = null
            )
        }
    }
}
