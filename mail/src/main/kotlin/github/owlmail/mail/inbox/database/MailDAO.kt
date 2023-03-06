package github.owlmail.mail.inbox.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import github.owlmail.mail.inbox.model.InboxSearchResponse

@Dao
interface MailDAO {

    @Query(
        "select * from conversation where (message like '%' || :folder || '%') and (body like '%' || :query || '%' or emailAddress like '%' || :query || '%' or subject like '%' || :query || '%') order by d desc limit :limit offset :offset"
    )
    suspend fun getAllMails(
        limit: Int,
        offset: Int,
        folder: String,
        query: String
    ): List<InboxSearchResponse.Body.SearchResponse.Conversation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMails(list: List<InboxSearchResponse.Body.SearchResponse.Conversation>)

    @Query("DELETE FROM conversation")
    suspend fun deleteAllMails()
}
