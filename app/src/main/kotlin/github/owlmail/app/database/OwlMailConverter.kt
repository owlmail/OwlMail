package github.owlmail.app.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import github.owlmail.contacts.model.ContactResponse
import github.owlmail.mail.detail.model.MailDetailResponse
import github.owlmail.mail.inbox.model.InboxSearchResponse

@ProvidedTypeConverter
class OwlMailConverter(private val moshi: Moshi) {
    private val emailAddressListType = Types.newParameterizedType(
        List::class.java,
        InboxSearchResponse.Body.SearchResponse.Conversation.EmailAddress::class.java
    )
    private val emailAddressMessageListType = Types.newParameterizedType(
        List::class.java,
        MailDetailResponse.Body.SearchConvResponse.Message.EmailAddress::class.java
    )
    private val messageListType = Types.newParameterizedType(
        List::class.java,
        InboxSearchResponse.Body.SearchResponse.Conversation.Message::class.java
    )

    private val multiPartListType = Types.newParameterizedType(
        List::class.java,
        MailDetailResponse.Body.SearchConvResponse.Message.MultiPart::class.java
    )

    @TypeConverter
    fun emailAddressToJSON(
        emailAddress: List<InboxSearchResponse.Body.SearchResponse.Conversation.EmailAddress?>?
    ): String? {
        return moshi.adapter<List<InboxSearchResponse.Body.SearchResponse.Conversation.EmailAddress?>?>(
            emailAddressListType
        )
            .toJson(emailAddress)
    }

    @TypeConverter
    fun emailAddressFromJSON(json: String): List<InboxSearchResponse.Body.SearchResponse.Conversation.EmailAddress?>? {
        return moshi.adapter<List<InboxSearchResponse.Body.SearchResponse.Conversation.EmailAddress?>?>(
            emailAddressListType
        )
            .fromJson(json)
    }

    @TypeConverter
    fun messageToJSON(message: List<InboxSearchResponse.Body.SearchResponse.Conversation.Message?>?): String? {
        return moshi.adapter<List<InboxSearchResponse.Body.SearchResponse.Conversation.Message?>?>(
            messageListType
        )
            .toJson(message)
    }

    @TypeConverter
    fun messageFromJSON(json: String): List<InboxSearchResponse.Body.SearchResponse.Conversation.Message?>? {
        return moshi.adapter<List<InboxSearchResponse.Body.SearchResponse.Conversation.Message?>?>(
            messageListType
        )
            .fromJson(json)
    }

    @TypeConverter
    fun emailAddressMessageToJSON(
        emailAddress: List<MailDetailResponse.Body.SearchConvResponse.Message.EmailAddress?>?
    ): String? {
        return moshi.adapter<List<MailDetailResponse.Body.SearchConvResponse.Message.EmailAddress?>?>(
            emailAddressMessageListType
        )
            .toJson(emailAddress)
    }

    @TypeConverter
    fun emailAddressMessageFromJSON(json: String): List<MailDetailResponse.Body.SearchConvResponse.Message.EmailAddress?>? {
        return moshi.adapter<List<MailDetailResponse.Body.SearchConvResponse.Message.EmailAddress?>?>(
            emailAddressMessageListType
        )
            .fromJson(json)
    }

    @TypeConverter
    fun multiPartToJSON(
        multiPart: List<MailDetailResponse.Body.SearchConvResponse.Message.MultiPart>?
    ): String? {
        return moshi.adapter<List<MailDetailResponse.Body.SearchConvResponse.Message.MultiPart?>?>(
            multiPartListType
        )
            .toJson(multiPart)
    }

    @TypeConverter
    fun multiPartFromJSON(json: String): List<MailDetailResponse.Body.SearchConvResponse.Message.MultiPart?>? {
        return moshi.adapter<List<MailDetailResponse.Body.SearchConvResponse.Message.MultiPart?>?>(
            multiPartListType
        )
            .fromJson(json)
    }

    @TypeConverter
    fun attrsToJSON(attrs: ContactResponse.Body.SearchGalResponse.Cn.Attrs): String? {
        return moshi.adapter(ContactResponse.Body.SearchGalResponse.Cn.Attrs::class.java)
            .toJson(attrs)
    }

    @TypeConverter
    fun attrsFromJSON(json: String): ContactResponse.Body.SearchGalResponse.Cn.Attrs? {
        return moshi.adapter(ContactResponse.Body.SearchGalResponse.Cn.Attrs::class.java)
            .fromJson(json)
    }
}
