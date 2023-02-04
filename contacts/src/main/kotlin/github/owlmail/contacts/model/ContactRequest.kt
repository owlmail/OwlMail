package github.owlmail.contacts.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContactRequest(
    @Json(name = "Body")
    val body: Body? = null
) {
    @JsonClass(generateAdapter = true)
    data class Body(
        @Json(name = "SearchGalRequest")
        val searchGalRequest: SearchGalRequest? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class SearchGalRequest(
            @Json(name = "_jsns")
            val jsns: String? = null,
            @Json(name = "limit")
            val limit: Int? = null,
            @Json(name = "name")
            val name: String? = null,
            @Json(name = "offset")
            val offset: Int? = null
        )
    }
}
