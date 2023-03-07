package github.owlmail.auth

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.owlmail.auth.api.AuthUseCase
import github.owlmail.core.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val dataStoreManager: DataStoreManager,
) : ViewModel() {

    val loginState = authUseCase.get()

    // logic for login function
    // flow or livedata to observe login state
    fun userLogin(userDetails: UserDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            authUseCase(userDetails.userId, userDetails.userPassword)
        }
    }

    fun tryAuthFromLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            authUseCase()
        }
    }

    private fun setAppTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            val preferences = dataStoreManager.readFromDataStore().first()
            val themeType = preferences[DataStoreManager.SET_THEME]
            if (themeType != null) {
                // get theme from datastore and set app theme
                AppCompatDelegate.setDefaultNightMode(themeType)
            } else {
                // if null set system default in datastore and set app theme
                dataStoreManager.setThemeValue(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }

    init {
        setAppTheme()
        tryAuthFromLocal()
    }
}
