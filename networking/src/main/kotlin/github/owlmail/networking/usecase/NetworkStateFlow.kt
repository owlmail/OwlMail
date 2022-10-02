package github.owlmail.networking.usecase

import github.owlmail.networking.state.NetworkState
import kotlinx.coroutines.flow.Flow

interface NetworkStateFlow {
    operator fun invoke(): Flow<NetworkState>
}
