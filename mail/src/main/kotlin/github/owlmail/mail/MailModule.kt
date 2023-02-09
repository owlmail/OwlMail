package github.owlmail.mail

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.owlmail.mail.api.MailDatabaseDeleteUseCase
import github.owlmail.mail.detail.DetailDAO
import github.owlmail.mail.inbox.database.MailDAO
import github.owlmail.mail.workermanager.NotificationManager
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MailModule {

    //move moshi and retrofit to networking
    @Provides
    @Singleton
    fun providesService(retrofit: Retrofit) = retrofit.create(MailService::class.java)

    @Provides
    @Singleton
    fun providesRepository(service: MailService) = MailRepository(service)

    @Provides
    @Singleton
    fun provideNotificationManager(@ApplicationContext context: Context) =
        NotificationManager(context)


    @Provides
    @Singleton
    fun provideMailDatabaseDeleteUseCase(
        mailDAO: MailDAO,
        detailDAO: DetailDAO
    ): MailDatabaseDeleteUseCase = MailDatabaseDeleteUseCaseImpl(mailDAO, detailDAO)
}