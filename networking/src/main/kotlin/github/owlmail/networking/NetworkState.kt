package github.owlmail.networking

sealed interface NetworkState {
    object Available : NetworkState
    object Unavailable : NetworkState
}
