package github.owlmail.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.owlmail.networking.AuthIntercepter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun providesService(retrofit: Retrofit) = retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideRepository(service: AuthService,authIntercepter: AuthIntercepter) = AuthRepository(authIntercepter,service)

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context) = preferencesDataStore("owl_mail").getValue(context,Preferences::javaClass)

    @Provides
    @Singleton
    fun provideDataStoreManager(dataStore: DataStore<Preferences>) = DataStoreManager(dataStore)
}