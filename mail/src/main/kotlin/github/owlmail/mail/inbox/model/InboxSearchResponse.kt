package github.owlmail.mail.inbox.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//c=conversation
@JsonClass(generateAdapter = true)
data class InboxSearchResponse(
    @Json(name = "Body")
    val body: Body? = null,
    @Json(name = "Header")
    val header: Header? = null,
    @Json(name = "_jsns")
    val jsns: String? = null
) {
    @JsonClass(generateAdapter = true)
    data class Body(
        @Json(name = "SearchResponse")
        val searchResponse: SearchResponse? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class SearchResponse(
            @Json(name = "c")
            val conversation: List<Conversation?>? = null,
            @Json(name = "_jsns")
            val jsns: String? = null,
            @Json(name = "more")
            val more: Boolean? = null,
            @Json(name = "offset")
            val offset: Int? = null,
            @Json(name = "sortBy")
            val sortBy: String? = null
        ) {

            @JsonClass(generateAdapter = true)
            @Entity(tableName = "conversation")
            data class Conversation(
                @Json(name = "d")
                val d: Long? = null,
                @Json(name = "e")
                val emailAddress: List<EmailAddress?>? = null,
                @Json(name = "f")
                val flags: String? = null,
                @Json(name = "fr")
                val body: String? = null,
                @Json(name = "id")
                @PrimaryKey val id: String = "",
                @Json(name = "m")
                val message: List<Message?>? = null,
                @Json(name = "n")
                val n: Int? = null,
                @Json(name = "sf")
                val sf: String? = null,
                @Json(name = "su")
                val subject: String? = null,
                @Json(name = "u")
                val u: Int? = null
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
                    val isSenderOrReceiver: String? = null //if f=from/sender, t=to/receiver
                )

                @JsonClass(generateAdapter = true)
                data class Message(
                    @Json(name = "f")
                    val f: String? = null,
                    @Json(name = "id")
                    val id: String? = null,
                    @Json(name = "l")
                    val l: String? = null,
                    @Json(name = "s")
                    val s: String? = null
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