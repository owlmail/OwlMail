package github.owlmail.mail.inbox

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MailDAO {
    @Query("SELECT * FROM conversation")
    fun getAllMails(): List<InboxSearchResponse.Body.SearchResponse.Conversation>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMails(list: List<InboxSearchResponse.Body.SearchResponse.Conversation>)
    
    @Query("DELETE FROM conversation")
    fun deleteAllMails()
}