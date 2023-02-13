package github.owlmail.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import github.owlmail.contacts.ContactDAO
import github.owlmail.contacts.model.ContactResponse
import github.owlmail.mail.detail.DetailDAO
import github.owlmail.mail.detail.model.MailDetailResponse
import github.owlmail.mail.inbox.database.MailDAO
import github.owlmail.mail.inbox.model.InboxSearchResponse

@TypeConverters(OwlMailConverter::class)
@Database(
    entities = [InboxSearchResponse.Body.SearchResponse.Conversation::class, MailDetailResponse.Body.SearchConvResponse.Message::class, ContactResponse.Body.SearchGalResponse.Cn::class],
    version = 4
)
abstract class OwlMailDatabase : RoomDatabase() {
    abstract fun getMailDAO(): MailDAO
    abstract fun getDetailDAO(): DetailDAO
    abstract fun getContactDAO(): ContactDAO
}
