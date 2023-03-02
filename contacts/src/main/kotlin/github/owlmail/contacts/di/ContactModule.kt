package github.owlmail.contacts.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import github.owlmail.contacts.ContactDAO
import github.owlmail.contacts.ContactDatabaseDeleteUseCaseImpl
import github.owlmail.contacts.ContactRepository
import github.owlmail.contacts.ContactService
import github.owlmail.contacts.api.ContactDatabaseDeleteUseCase
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ContactModule {
    @Provides
    @Singleton
    fun providesService(retrofit: Retrofit) = retrofit.create(ContactService::class.java)

    @Provides
    @Singleton
    fun providesRepository(service: ContactService) = ContactRepository(service)

    @Provides
    @Singleton
    fun providesContactDatabaseDeleteUseCase(contactDAO: ContactDAO): ContactDatabaseDeleteUseCase =
        ContactDatabaseDeleteUseCaseImpl(contactDAO)
}
