package github.owlmail.mail.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MailDetailRequest(
    @Json(name = "Body")
    val body: Body? = null,
    @Json(name = "Header")
    val header: Header? = null
) {
    @JsonClass(generateAdapter = true)
    data class Body(
        @Json(name = "SearchConvRequest")
        val searchConvRequest: SearchConvRequest? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class SearchConvRequest(
            @Json(name = "cid")
            val cid: String? = null,
            @Json(name = "fetch")
            val fetch: String? = null,
            @Json(name = "header")
            val header: List<Header?>? = null,
            @Json(name = "html")
            val html: Int? = null,
            @Json(name = "_jsns")
            val jsns: String? = null,
            @Json(name = "limit")
            val limit: Int? = null,
            @Json(name = "locale")
            val locale: Locale? = null,
            @Json(name = "max")
            val max: Int? = null,
            @Json(name = "needExp")
            val needExp: Int? = null,
            @Json(name = "offset")
            val offset: Int? = null,
            @Json(name = "query")
            val query: String? = null,
            @Json(name = "recip")
            val recip: String? = null,
            @Json(name = "sortBy")
            val sortBy: String? = null,
            @Json(name = "tz")
            val tz: Tz? = null
        ) {
            @JsonClass(generateAdapter = true)
            data class Header(
                @Json(name = "n")
                val n: String? = null
            )

            @JsonClass(generateAdapter = true)
            data class Locale(
                @Json(name = "_content")
                val content: String? = null
            )

            @JsonClass(generateAdapter = true)
            data class Tz(
                @Json(name = "id")
                val id: String? = null
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
            @Json(name = "account")
            val account: Account? = null,
            @Json(name = "csrfToken")
            val csrfToken: String? = null,
            @Json(name = "_jsns")
            val jsns: String? = null,
            @Json(name = "session")
            val session: Session? = null,
            @Json(name = "userAgent")
            val userAgent: UserAgent? = null
        ) {
            @JsonClass(generateAdapter = true)
            data class Account(
                @Json(name = "by")
                val `by`: String? = null,
                @Json(name = "_content")
                val content: String? = null
            )

            @JsonClass(generateAdapter = true)
            data class Session(
                @Json(name = "_content")
                val content: Int? = null,
                @Json(name = "id")
                val id: Int? = null
            )

            @JsonClass(generateAdapter = true)
            data class UserAgent(
                @Json(name = "name")
                val name: String? = null,
                @Json(name = "version")
                val version: String? = null
            )
        }
    }
}