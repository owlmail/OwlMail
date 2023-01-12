package github.owlmail.auth

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesMoshi() = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun providesRetrofit(moshi: Moshi) = Retrofit.Builder()
        .baseUrl("https://mail.nitrkl.ac.in/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun providesService(retrofit: Retrofit) = retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideRepository(service: AuthService) = AuthRepository(service)

}