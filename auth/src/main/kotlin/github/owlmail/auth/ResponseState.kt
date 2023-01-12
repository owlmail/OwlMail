package github.owlmail.auth

sealed class ResponseState<T>(val data: T?, val message: String?) {
    class Success<T>(data: T) : ResponseState<T>(data, null)
    class Error<T>(message: String) : ResponseState<T>(null, message)
}
