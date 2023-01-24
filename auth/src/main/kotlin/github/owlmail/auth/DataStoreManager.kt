package github.owlmail.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class DataStoreManager(private val dataStore: DataStore<Preferences>) {

    companion object {

        val userId = stringPreferencesKey("User_id")
        val password = stringPreferencesKey("Password")
    }

    suspend fun saveToDataStore(userDetails: UserDetails) = withContext(Dispatchers.IO) {
        dataStore.edit {
            it[userId] = userDetails.userId
            it[password] = userDetails.userPassword
        }
    }

    fun readFromDataStore() = dataStore.data
}