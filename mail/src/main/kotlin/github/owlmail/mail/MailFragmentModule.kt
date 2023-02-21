package github.owlmail.mail

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import github.owlmail.mail.detail.MailDetailAdapter
import github.owlmail.mail.inbox.MailAdapter

@Module
@InstallIn(FragmentComponent::class)
object MailFragmentModule {

    @Provides
    @FragmentScoped
    fun provideMailDetailAdapter() = MailDetailAdapter()

    @Provides
    @FragmentScoped
    fun provideMailAdapter() = MailAdapter()

    @Provides
    @FragmentScoped
    fun provideMailBoxTabAdapter(fragment: Fragment) = MailBoxTabAdapter(fragment as MailBoxHostFragment)
}