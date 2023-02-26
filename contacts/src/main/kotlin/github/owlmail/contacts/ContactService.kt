package github.owlmail.contacts

import github.owlmail.contacts.model.ContactRequest
import github.owlmail.contacts.model.ContactResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ContactService {
    @POST("service/soap/SearchGalRequest")
    suspend fun getContactList(@Body contactRequest: ContactRequest): Response<ContactResponse>
}
