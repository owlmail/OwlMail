package github.owlmail.mail.detail


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
            @Json(name = "_jsns")
            val jsns: String? = null,
            @Json(name = "limit")
            val limit: Int? = null,
            @Json(name = "offset")
            val offset: Int? = null,
            @Json(name = "html")
            val html: Int? = null
        )
    }
}