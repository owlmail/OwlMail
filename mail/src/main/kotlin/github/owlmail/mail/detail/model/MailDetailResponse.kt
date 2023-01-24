package github.owlmail.mail.detail.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MailDetailResponse(
    @Json(name = "Body")
    val body: Body? = null,
    @Json(name = "Header")
    val header: Header? = null,
    @Json(name = "_jsns")
    val jsns: String? = null
) {
    @JsonClass(generateAdapter = true)
    data class Body(
        @Json(name = "SearchConvResponse")
        val searchConvResponse: SearchConvResponse? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class SearchConvResponse(
            @Json(name = "_jsns")
            val jsns: String? = null,
            @Json(name = "m")
            val message: List<M?>? = null,
            @Json(name = "more")
            val more: Boolean? = null,
            @Json(name = "offset")
            val offset: String? = null,
            @Json(name = "sortBy")
            val sortBy: String? = null
        ) {
            @JsonClass(generateAdapter = true)
            data class M(
                @Json(name = "cid")
                val cid: String? = null,
                @Json(name = "cm")
                val cm: Boolean? = null,
                @Json(name = "d")
                val date: Long? = null,
                @Json(name = "e")
                val emailAdd: List<E?>? = null,
                @Json(name = "fr")
                val body: String? = null,
                @Json(name = "id")
                val id: String? = null,
                @Json(name = "l")
                val folder: String? = null,
                @Json(name = "mid")
                val mid: String? = null,
                @Json(name = "mp")
                val mp: List<Mp?>? = null,
                @Json(name = "rev")
                val rev: Int? = null,
                @Json(name = "s")
                val messageSize: Int? = null,
                @Json(name = "sd")
                val sd: Long? = null,
                @Json(name = "sf")
                val sf: String? = null,
                @Json(name = "su")
                val subject: String? = null
            ) {
                @JsonClass(generateAdapter = true)
                data class E(
                    @Json(name = "a")
                    val a: String? = null,
                    @Json(name = "d")
                    val d: String? = null,
                    @Json(name = "t")
                    val t: String? = null
                )

                @JsonClass(generateAdapter = true)
                data class Mp(
                    @Json(name = "body")
                    val body: Boolean? = null,
                    @Json(name = "content")
                    val content: String? = null,
                    @Json(name = "ct")
                    val contentType: String? = null,
                    @Json(name = "part")
                    val part: String? = null,
                    @Json(name = "s")
                    val s: Int? = null
                )
            }
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
            val jsns: String? = null,
            @Json(name = "session")
            val session: Session? = null
        ) {
            @JsonClass(generateAdapter = true)
            data class Change(
                @Json(name = "token")
                val token: Int? = null
            )

            @JsonClass(generateAdapter = true)
            data class Session(
                @Json(name = "_content")
                val content: String? = null,
                @Json(name = "id")
                val id: String? = null
            )
        }
    }
}