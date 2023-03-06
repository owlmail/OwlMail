package github.owlmail.auth

import github.owlmail.auth.api.AuthUseCase
import github.owlmail.auth.api.LogoutUseCase
import github.owlmail.contacts.api.ContactDatabaseDeleteUseCase
import github.owlmail.mail.api.MailDatabaseDeleteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class LogoutUseCaseImpl(
    private val dataStoreManager: github.owlmail.core.DataStoreManager,
    private val authUseCase: AuthUseCase,
    private val mailDatabaseDeleteUseCase: MailDatabaseDeleteUseCase,
    private val contactDatabaseDeleteUseCase: ContactDatabaseDeleteUseCase
) : LogoutUseCase {
    override suspend fun invoke() = withContext(Dispatchers.IO) {
        awaitAll(
            async {
                dataStoreManager.clearDataStore()
                authUseCase()
            },
            async {
                mailDatabaseDeleteUseCase()
            },
            async {
                contactDatabaseDeleteUseCase()
            }
        )
        Unit
    }
}
