package github.owlmail.networking

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {
    @Provides
    @Singleton
    fun providesAuthIntercepter() = AuthIntercepter()

    @Provides
    @Singleton
    fun providesOkHttpClient(authIntercepter: AuthIntercepter)= OkHttpClient.Builder()
        .addInterceptor(authIntercepter)
        .addNetworkInterceptor(StethoInterceptor())
        .build()

    @Provides
    @Singleton
    fun providesMoshi() =
        Moshi.Builder().add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun providesRetrofit(moshi: Moshi, okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl("https://mail.nitrkl.ac.in/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
}