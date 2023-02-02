package github.owlmail.mail.inbox

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class OwlMailConverter(private val moshi: Moshi) {
    private val emailAddressListType = Types.newParameterizedType(List::class.java,InboxSearchResponse.Body.SearchResponse.Conversation.EmailAddress::class.java)
    private val messageListType = Types.newParameterizedType(List::class.java,InboxSearchResponse.Body.SearchResponse.Conversation.Message::class.java)
    @TypeConverter
    fun emailAddressToJSON(emailAddress: List<InboxSearchResponse.Body.SearchResponse.Conversation.EmailAddress?>?): String? {
        return moshi.adapter<List<InboxSearchResponse.Body.SearchResponse.Conversation.EmailAddress?>?>(emailAddressListType)
            .toJson(emailAddress)
    }

    @TypeConverter
    fun emailAddressFromJSON(json: String): List<InboxSearchResponse.Body.SearchResponse.Conversation.EmailAddress?>? {
        return moshi.adapter<List<InboxSearchResponse.Body.SearchResponse.Conversation.EmailAddress?>?>(emailAddressListType)
            .fromJson(json)
    }

    @TypeConverter
    fun messageToJSON(message: List<InboxSearchResponse.Body.SearchResponse.Conversation.Message?>?): String? {
        return moshi.adapter<List<InboxSearchResponse.Body.SearchResponse.Conversation.Message?>?>(messageListType)
            .toJson(message)
    }

    @TypeConverter
    fun messageFromJSON(json: String): List<InboxSearchResponse.Body.SearchResponse.Conversation.Message?>? {
        return moshi.adapter<List<InboxSearchResponse.Body.SearchResponse.Conversation.Message?>?>(messageListType)
            .fromJson(json)
    }
}