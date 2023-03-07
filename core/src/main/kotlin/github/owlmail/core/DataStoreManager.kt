package github.owlmail.core

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataStoreManager(private val dataStore: DataStore<Preferences>) {

    companion object {

        val USER_ID = stringPreferencesKey("User_id")
        val PASSWORD = stringPreferencesKey("Password")
        val NOTIFICATION_TIME_STAMP = longPreferencesKey("Notification_last_sync")
        val SET_THEME = intPreferencesKey("Set_theme")
    }

    suspend fun saveToDataStore(userId: String, userPassword: String) =
        withContext(Dispatchers.IO) {
            dataStore.edit {
                it[USER_ID] = userId
                it[PASSWORD] = userPassword
            }
        }

    fun readFromDataStore() = dataStore.data

    suspend fun clearDataStore() {
        dataStore.edit {
            it.clear()
        }
    }

    suspend fun setThemeValue(thisTheme: Int) = withContext(Dispatchers.IO) {
        dataStore.edit {
            it[SET_THEME] = thisTheme
        }
    }

    suspend fun saveToDataStore(timeStamp: Long) = withContext(Dispatchers.IO) {
        dataStore.edit {
            it[NOTIFICATION_TIME_STAMP] = timeStamp
        }
    }
}
