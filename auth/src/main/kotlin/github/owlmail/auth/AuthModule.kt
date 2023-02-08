package github.owlmail.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.owlmail.auth.api.AuthUseCase
import github.owlmail.auth.api.LogoutUseCase
import github.owlmail.networking.AuthIntercepter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun providesService(retrofit: Retrofit) = retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideRepository(service: AuthService, authIntercepter: AuthIntercepter) =
        AuthRepository(authIntercepter, service)

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context) =
        preferencesDataStore("owl_mail").getValue(context, Preferences::javaClass)

    @Provides
    @Singleton
    fun provideDataStoreManager(dataStore: DataStore<Preferences>) = DataStoreManager(dataStore)

    @Provides
    @Singleton
    fun provideAuthUseCase(
        authRepository: AuthRepository,
        dataStoreManager: DataStoreManager
    ): AuthUseCase = AuthUseCaseImpl(authRepository, dataStoreManager)

    @Provides
    @Singleton
    fun provideLogoutUseCase(
        dataStoreManager: DataStoreManager,
        authUseCase: AuthUseCase
    ): LogoutUseCase = LogoutUseCaseImpl(dataStoreManager, authUseCase)
}