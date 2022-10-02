package github.owlmail.networking

import kotlinx.coroutines.flow.Flow

interface NetworkStateFlow {
    operator fun invoke(): Flow<NetworkState>
}
