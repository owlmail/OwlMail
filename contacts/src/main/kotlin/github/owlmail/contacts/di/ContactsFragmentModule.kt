package github.owlmail.contacts.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import github.owlmail.contacts.ContactAdapter

@Module
@InstallIn(FragmentComponent::class)
object ContactsFragmentModule {

    @Provides
    @FragmentScoped
    fun provideContactAdapter() = ContactAdapter()
}
