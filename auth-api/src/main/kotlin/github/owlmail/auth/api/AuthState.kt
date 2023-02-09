package github.owlmail.auth.api

sealed interface AuthState {
    object AUTHENTICATED: AuthState
    object NON_AUTHENTICATED: AuthState
    object UNKNOWN: AuthState
}