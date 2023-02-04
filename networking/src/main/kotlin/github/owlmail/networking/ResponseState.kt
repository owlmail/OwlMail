package github.owlmail.networking

import retrofit2.Response

sealed class ResponseState<out T>(val data: T? = null, val message: String? = null) {
    class Success<out T>(data: T) : ResponseState<T>(data)
    class Error(message: String?) : ResponseState<Nothing>(null, message)

    //    object Loading: ResponseState<Nothing>()
    object Empty : ResponseState<Nothing>()
}

fun <T> Response<T>.mapToResponseState() = when (this.isSuccessful) {
    true -> ResponseState.Success(this.body())
//    this.headers()
    else -> ResponseState.Error(this.message())
}
