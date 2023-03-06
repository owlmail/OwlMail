package github.owlmail.contacts

import github.owlmail.contacts.model.ContactRequest

class ContactRepository(
    private val service: ContactService
) {
    suspend fun getContactList(contactRequest: ContactRequest) =
        service.getContactList(contactRequest)
}
