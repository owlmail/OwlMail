package github.owlmail.app

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.owlmail.app.database.OwlMailConverter
import github.owlmail.app.database.OwlMailDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOwlMailDatabase(@ApplicationContext context: Context, moshi: Moshi) =
        Room.databaseBuilder(
            context,
            OwlMailDatabase::class.java,
            "owlmail_db"
        )
            .fallbackToDestructiveMigration()
            .addTypeConverter(OwlMailConverter(moshi))
            .build()

    @Provides
    @Singleton
    fun provideMailDAO(owlMailDatabase: OwlMailDatabase) =
        owlMailDatabase.getMailDAO()

    @Provides
    @Singleton
    fun provideDetailDAO(owlMailDatabase: OwlMailDatabase) =
        owlMailDatabase.getDetailDAO()

    @Provides
    @Singleton
    fun provideContactDAO(owlMailDatabase: OwlMailDatabase) =
        owlMailDatabase.getContactDAO()
}
