package github.owlmail.mail.inbox

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(OwlMailConverter::class)
@Database(entities = [InboxSearchResponse.Body.SearchResponse.Conversation::class], version = 1)
abstract class OwlMailDatabase : RoomDatabase() {
    abstract fun getMailDAO(): MailDAO
}