package github.owlmail.mail

import github.owlmail.mail.detail.model.MailDetailRequest
import github.owlmail.mail.detail.model.MailDetailResponse
import github.owlmail.mail.inbox.model.InboxSearchRequest
import github.owlmail.mail.inbox.model.InboxSearchResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MailService {
    @POST("service/soap/SearchRequest")
    suspend fun getMailList(
        @Body inboxSearchRequest: InboxSearchRequest,
//        @Header("X-Zimbra-Csrf-Token") csrfToken: String = "0_c9e079d6ec49938f691726c3bbb00501b57726bd",
//        @Header("Cookie") authToken: String = "ZM_AUTH_TOKEN=0_f8450d2ed32cce48c7bd0c0daa8db88f3749c281_69643d33363a33386132363661312d353834332d343233652d393461372d3236373436313565643766393b6578703d31333a313637333935313136353539323b747970653d363a7a696d6272613b7469643d393a3437343033393430333b76657273696f6e3d31333a382e362e305f47415f313135333b637372663d313a313b"
    ): Response<InboxSearchResponse>

    @POST("service/soap/SearchConvRequest")
    suspend fun getMailDetails(@Body mailDetailRequest: MailDetailRequest): Response<MailDetailResponse>

    @GET("service/home/~/")
    suspend fun getMailAttachment(
        @Query("id") messageId : String?,
        @Query("part") part: String?,
        @Query("auth") authType: String = "co"
    ): ResponseBody
}