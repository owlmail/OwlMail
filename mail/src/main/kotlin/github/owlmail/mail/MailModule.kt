package github.owlmail.mail

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.owlmail.mail.inbox.database.OwlMailConverter
import github.owlmail.mail.inbox.database.OwlMailDatabase
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
    fun provideOwlMailDatabase(@ApplicationContext context: Context, moshi: Moshi) =
        Room.databaseBuilder(context, OwlMailDatabase::class.java, "owlmail_db")
            .fallbackToDestructiveMigration()
            .addTypeConverter(OwlMailConverter(moshi))
            .build()

    @Provides
    @Singleton
    fun provideMailDAO(owlMailDatabase: OwlMailDatabase) = owlMailDatabase.getMailDAO()

    @Provides
    @Singleton
    fun provideDetailDAO(owlMailDatabase: OwlMailDatabase) = owlMailDatabase.getDetailDAO()

    @Provides
    @Singleton
    fun provideContactDAO(owlMailDatabase: OwlMailDatabase) = owlMailDatabase.getContactDAO()
}