package github.owlmail.mail

import github.owlmail.mail.detail.MailDetailRequest

class MailRepository(
    private val service: MailService
) {
    suspend fun getMailList(inboxSearchRequest: InboxSearchRequest) =
        service.getMailList(inboxSearchRequest)

    suspend fun getMailDetail(mailDetailRequest: MailDetailRequest) = service.getMailDetails(mailDetailRequest)
}