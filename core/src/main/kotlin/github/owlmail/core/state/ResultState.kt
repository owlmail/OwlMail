package github.owlmail.core.state

sealed interface ResultState<out T> {
    data class Success<out T>(val data: T? = null) : ResultState<T>
    data class Error<out T>(val exception: Exception, val data: T? = null) : ResultState<T>
    data object Loading : ResultState<Nothing>
    data object Empty : ResultState<Nothing>

    fun mapToCallbacks(
        emptyState: (() -> Unit)? = null,
        loadingState: (() -> Unit)? = null,
        successState: (() -> Unit)? = null,
        errorState: (() -> Unit)? = null
    ) = when (this) {
        is Empty -> emptyState?.invoke()
        is Loading -> loadingState?.invoke()
        is Success -> successState?.invoke()
        is Error -> errorState?.invoke()
    }
}
