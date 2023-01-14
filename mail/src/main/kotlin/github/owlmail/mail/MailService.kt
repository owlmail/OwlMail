package github.owlmail.mail

import retrofit2.http.Body
import retrofit2.http.POST

interface MailService {
    @POST("service/soap/SearchRequest")
    suspend fun getMailList(@Body inboxSearchRequest: InboxSearchRequest): InboxSearchResponse
}