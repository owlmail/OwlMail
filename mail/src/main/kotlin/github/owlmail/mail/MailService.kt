package github.owlmail.mail

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface MailService {
    @POST("service/soap/SearchRequest")
    suspend fun getMailList(
        @Body inboxSearchRequest: InboxSearchRequest,
//        @Header("Cookie") authToken: String = "ZM_AUTH_TOKEN=0_176565691e5969ef19251ca9daf0152e749eb24a_69643d33363a33386132363661312d353834332d343233652d393461372d3236373436313565643766393b6578703d31333a313637333736313238373230333b747970653d363a7a696d6272613b7469643d393a3136373539353933383b76657273696f6e3d31333a382e362e305f47415f313135333b637372663d313a313b; JSESSIONID=18tbqlvmvg8ol1gv5iioh3gkmv"
    ): InboxSearchResponse
}