package github.owlmail.auth

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    // retrofit get/post calls
    @POST("service/soap/AuthRequest")

    suspend fun makeAuthRequest(@Body authRequest: RequestAuth): Response<ResponseAuth>
}
