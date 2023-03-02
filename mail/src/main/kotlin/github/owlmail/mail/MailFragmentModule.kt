package github.owlmail.mail

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import github.owlmail.mail.detail.MailDetailAdapter
import github.owlmail.mail.detail.OnMailDetailClick
import github.owlmail.mail.inbox.MailAdapter
import github.owlmail.mail.inbox.OnMailClick

@Module
@InstallIn(FragmentComponent::class)
object MailFragmentModule {

    @Provides
    @FragmentScoped
    fun provideMailDetailAdapter(fragment: Fragment) =
        MailDetailAdapter(fragment as OnMailDetailClick)

    @Provides
    @FragmentScoped
    fun provideMailAdapter(fragment: Fragment) = MailAdapter(fragment as OnMailClick)

    @Provides
    @FragmentScoped
    fun provideMailBoxTabAdapter(fragment: Fragment) =
        MailBoxTabAdapter(fragment as MailBoxHostFragment)
}
