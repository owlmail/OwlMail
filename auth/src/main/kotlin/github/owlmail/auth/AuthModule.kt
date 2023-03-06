package github.owlmail.auth

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.owlmail.auth.api.AuthUseCase
import github.owlmail.auth.api.LogoutUseCase
import github.owlmail.contacts.api.ContactDatabaseDeleteUseCase
import github.owlmail.core.DataStoreManager
import github.owlmail.mail.api.MailDatabaseDeleteUseCase
import github.owlmail.networking.AuthInterceptor
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun providesService(retrofit: Retrofit) = retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideRepository(service: AuthService, authInterceptor: AuthInterceptor) =
        AuthRepository(authInterceptor, service)

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context) =
        preferencesDataStore("owl_mail").getValue(context, Preferences::javaClass)

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
        authUseCase: AuthUseCase,
        mailDatabaseDeleteUseCase: MailDatabaseDeleteUseCase,
        contactDatabaseDeleteUseCase: ContactDatabaseDeleteUseCase

    ): LogoutUseCase = LogoutUseCaseImpl(
        dataStoreManager,
        authUseCase,
        mailDatabaseDeleteUseCase,
        contactDatabaseDeleteUseCase
    )
}
