package github.owlmail.contacts

import github.owlmail.contacts.api.ContactDatabaseDeleteUseCase

class ContactDatabaseDeleteUseCaseImpl(private val contactDao: ContactDAO) :
    ContactDatabaseDeleteUseCase {
    override suspend fun invoke() {
        contactDao.deleteAllContacts()
    }
}
