package github.owlmail.contacts.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContactResponse(
    @Json(name = "Body")
    val body: Body? = null,
    @Json(name = "Header")
    val header: Header? = null,
    @Json(name = "_jsns")
    val jsns: String? = null
) {
    @JsonClass(generateAdapter = true)
    data class Body(
        @Json(name = "SearchGalResponse")
        val searchGalResponse: SearchGalResponse? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class SearchGalResponse(
            @Json(name = "cn")
            val cn: List<Cn?>? = null,
            @Json(name = "_jsns")
            val jsns: String? = null,
            @Json(name = "more")
            val more: Boolean? = null,
            @Json(name = "offset")
            val offset: Int? = null,
            @Json(name = "paginationSupported")
            val paginationSupported: Boolean? = null,
            @Json(name = "sortBy")
            val sortBy: String? = null
        ) {
            @JsonClass(generateAdapter = true)
            @Entity(tableName = "contact")
            data class Cn(
                @Json(name = "_attrs")
                val attrs: Attrs? = null,
                @Json(name = "d")
                val d: Long? = null,
                @Json(name = "fileAsStr")
                val fileAsStr: String? = null,
                @Json(name = "id")
                @PrimaryKey
                val id: String = "",
                @Json(name = "l")
                val l: String? = null,
                @Json(name = "ref")
                val ref: String? = null,
                @Json(name = "rev")
                val rev: Int? = null,
                @Json(name = "sf")
                val sf: String? = null
            ) {
                @JsonClass(generateAdapter = true)
                data class Attrs(
                    @Json(name = "createTimeStamp")
                    val createTimeStamp: String? = null,
                    @Json(name = "email")
                    val email: String? = null,
                    @Json(name = "email2")
                    val email2: String? = null,
                    @Json(name = "fileAs")
                    val fileAs: String? = null,
                    @Json(name = "firstName")
                    val firstName: String? = null,
                    @Json(name = "fullName")
                    val fullName: String? = null,
                    @Json(name = "lastName")
                    val lastName: String? = null,
                    @Json(name = "modifyTimeStamp")
                    val modifyTimeStamp: String? = null,
                    @Json(name = "notes")
                    val notes: String? = null,
                    @Json(name = "objectClass")
                    val objectClass: List<String?>? = null,
                    @Json(name = "zimbraId")
                    val zimbraId: String? = null,
                    @Json(name = "zimbraNotes")
                    val zimbraNotes: String? = null
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
