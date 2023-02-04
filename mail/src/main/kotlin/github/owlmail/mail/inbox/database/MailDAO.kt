package github.owlmail.mail.inbox.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import github.owlmail.mail.inbox.model.InboxSearchResponse

@Dao
interface MailDAO {
    @Query("SELECT * FROM conversation")
    suspend fun getAllMails(): List<InboxSearchResponse.Body.SearchResponse.Conversation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMails(list: List<InboxSearchResponse.Body.SearchResponse.Conversation>)

    @Query("DELETE FROM conversation")
    suspend fun deleteAllMails()
}
