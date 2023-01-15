package github.owlmail.mail

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
object MailModule {

    //move moshi and retrofit to networking
    @Provides
    @Singleton
    fun providesService(retrofit: Retrofit) = retrofit.create(MailService::class.java)

    @Provides
    @Singleton
    fun providesRepository(service: MailService) = MailRepository(service)
}