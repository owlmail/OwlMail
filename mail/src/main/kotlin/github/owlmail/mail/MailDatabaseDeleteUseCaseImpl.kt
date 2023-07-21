package github.owlmail.mail

import github.owlmail.mail.api.MailDatabaseDeleteUseCase
import github.owlmail.mail.detail.DetailDAO
import github.owlmail.mail.inbox.database.MailDAO

class MailDatabaseDeleteUseCaseImpl(
    private val mailDAO: MailDAO,
    private val detailDAO: DetailDAO
) : MailDatabaseDeleteUseCase {
    override suspend fun invoke() {
        mailDAO.deleteAllMails()
        detailDAO.deleteAllMessage()
    }
}