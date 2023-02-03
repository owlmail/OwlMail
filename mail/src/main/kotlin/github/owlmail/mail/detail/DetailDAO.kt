package github.owlmail.mail.detail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import github.owlmail.mail.detail.model.MailDetailResponse

@Dao
interface DetailDAO {
    @Query("SELECT * FROM message")
    suspend fun getAllMessage(): List<MailDetailResponse.Body.SearchConvResponse.Message>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMessage(list: List<MailDetailResponse.Body.SearchConvResponse.Message>)

    @Query("DELETE FROM message")
    suspend fun deleteAllMessage()
}