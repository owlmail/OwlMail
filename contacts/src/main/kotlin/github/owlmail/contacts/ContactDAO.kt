package github.owlmail.contacts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import github.owlmail.contacts.model.ContactResponse

@Dao
interface ContactDAO {
    @Query("SELECT * FROM contact")
    suspend fun getAllContacts(): List<ContactResponse.Body.SearchGalResponse.Cn>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllContacts(list: List<ContactResponse.Body.SearchGalResponse.Cn>)

    @Query("DELETE FROM contact")
    suspend fun deleteAllContacts()
}