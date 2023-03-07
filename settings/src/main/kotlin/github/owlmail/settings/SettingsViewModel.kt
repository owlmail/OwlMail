package github.owlmail.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.owlmail.auth.api.LogoutUseCase
import github.owlmail.core.DataStoreManager
import github.owlmail.core.DataStoreManager.Companion.USER_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    val userId = dataStoreManager.readFromDataStore().map {
        it[USER_ID]
    }.stateIn(viewModelScope, SharingStarted.Lazily, "")

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            logoutUseCase()
        }
    }

    fun setOppositeTheme() {
        // check present theme and set other theme
        viewModelScope.launch {
            val themeType = AppCompatDelegate.getDefaultNightMode()
            val newTheme = when (themeType) {
                AppCompatDelegate.MODE_NIGHT_NO -> AppCompatDelegate.MODE_NIGHT_YES
                AppCompatDelegate.MODE_NIGHT_YES -> AppCompatDelegate.MODE_NIGHT_NO
                else -> AppCompatDelegate.MODE_NIGHT_YES
            }
            dataStoreManager.setThemeValue(newTheme)
            AppCompatDelegate.setDefaultNightMode(newTheme)
        }
    }
}
