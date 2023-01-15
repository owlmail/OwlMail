package github.owlmail.auth

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun provideRepository(service: AuthService) = AuthRepository(service)

}