package github.owlmail.mail.inbox.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InboxSearchRequest(
    @Json(name = "Body")
    val body: Body? = null
) {
    @JsonClass(generateAdapter = true)
    data class Body(
        @Json(name = "SearchRequest")
        val searchRequest: SearchRequest? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class SearchRequest(
            @Json(name = "_jsns")
            val jsns: String? = null,
            @Json(name = "limit")
            val limit: Int? = null,
            @Json(name = "offset")
            val offset: Int? = null,
            @Json(name = "query")
            val query: String? = null
        )
    }
}
