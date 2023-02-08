package github.owlmail.auth

import github.owlmail.auth.api.AuthUseCase
import github.owlmail.auth.api.LogoutUseCase

class LogoutUseCaseImpl(private val dataStoreManager: DataStoreManager, private val authUseCase: AuthUseCase):LogoutUseCase {
    override suspend fun invoke() {
        dataStoreManager.clearDataStore()
        authUseCase.invoke()
    }
}