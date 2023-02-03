package github.owlmail.mail.inbox.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import github.owlmail.mail.detail.DetailDAO
import github.owlmail.mail.detail.model.MailDetailResponse
import github.owlmail.mail.inbox.model.InboxSearchResponse

@TypeConverters(OwlMailConverter::class)
@Database(entities = [InboxSearchResponse.Body.SearchResponse.Conversation::class,MailDetailResponse.Body.SearchConvResponse.Message::class], version = 2)
abstract class OwlMailDatabase : RoomDatabase() {
    abstract fun getMailDAO(): MailDAO
    abstract fun getDetailDAO(): DetailDAO
}
