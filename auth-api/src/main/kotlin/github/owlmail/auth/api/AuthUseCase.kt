package github.owlmail.auth.api

import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    suspend operator fun invoke(userId: String, userPassword: String)
    fun get(): Flow<AuthState>
    suspend operator fun invoke()
}
