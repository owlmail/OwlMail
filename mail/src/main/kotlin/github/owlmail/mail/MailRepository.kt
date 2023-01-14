package github.owlmail.mail

class MailRepository(
    private val service: MailService
) {
    suspend fun getMailList(inboxSearchRequest: InboxSearchRequest) =
        service.getMailList(inboxSearchRequest)
}