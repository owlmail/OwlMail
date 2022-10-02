package github.owlmail.networking.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.owlmail.networking.provider.NetworkStateFlowProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingDiModule {

    @Provides
    @Singleton
    internal fun provideNetworkStateFlow(@ApplicationContext context: Context) =
        NetworkStateFlowProvider(context)
}
