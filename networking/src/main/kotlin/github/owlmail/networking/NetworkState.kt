package github.owlmail.networking

sealed interface NetworkState {
    data object Available : NetworkState
    data object Unavailable : NetworkState
    data object Unknown : NetworkState

    fun mapToCallbacks(
        availableState: (() -> Unit)? = null,
        unavailableState: (() -> Unit)? = null,
        unknownState: (() -> Unit)? = null
    ) = when (this) {
        is Available -> availableState?.invoke()
        is Unavailable -> unavailableState?.invoke()
        is Unknown -> unknownState?.invoke()
    }
}
