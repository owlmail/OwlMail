package github.owlmail.mail.detail.model

import androidx.room.Entity
import androidx.room.PrimaryKey
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
            val message: List<Message?>? = null,
            @Json(name = "more")
            val more: Boolean? = null,
            @Json(name = "offset")
            val offset: String? = null,
            @Json(name = "sortBy")
            val sortBy: String? = null
        ) {
            @JsonClass(generateAdapter = true)
            @Entity(tableName = "message")
            data class Message(
                @Json(name = "cid")
                val cid: String? = null,
                @Json(name = "f")
                val flags: String? = null,
                @Json(name = "cm")
                val cm: Boolean? = null,
                @Json(name = "d")
                val date: Long? = null,
                @Json(name = "e")
                val emailAdd: List<EmailAddress?>? = null,
                @Json(name = "fr")
                val body: String? = null,
                @Json(name = "id")
                @PrimaryKey
                val id: String = "",
                @Json(name = "l")
                val folder: String? = null,
                @Json(name = "mid")
                val mid: String? = null,
                @Json(name = "mp")
                val multiPart: List<MultiPart?>? = null,
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
                data class EmailAddress(
                    @Json(name = "a")
                    val mailAddress: String? = null,
                    @Json(name = "d")
                    val firstName: String? = null,
                    @Json(name = "p")
                    val fullName: String? = null,
                    @Json(name = "t")
                    val isSenderOrReceiver: String? = null
                )

                @JsonClass(generateAdapter = true)
                data class MultiPart(
                    @Json(name = "body")
                    val body: Boolean? = null,
                    @Json(name = "content")
                    val content: String? = null,
                    @Json(name = "ct")
                    val contentType: String? = null,
                    @Json(name = "cd")
                    val contentDescription: String? = null,
                    @Json(name = "filename")
                    val fileName: String? = null,
                    @Json(name = "mp")
                    val multiPart: List<MultiPart?>? = null,
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
