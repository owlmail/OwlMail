package github.owlmail.mail.detail.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MailDetailRequest(
    @Json(name = "Body")
    val body: Body? = null
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
            @Json(name = "html")
            val html: Int? = null,
            @Json(name = "_jsns")
            val jsns: String? = null,
            @Json(name = "limit")
            val limit: Int? = null,
            @Json(name = "max")
            val max: Int? = null,
            @Json(name = "needExp")
            val needExp: Int? = null,
            @Json(name = "offset")
            val offset: Int? = null,
            @Json(name = "query")
            val query: String? = null,
            @Json(name = "recip")
            val recip: String? = null
        )
    }
}
