package github.owlmail.auth.api

import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    suspend fun invoke(userId: String, userPassword: String)
    fun get(): Flow<AuthState>
    suspend fun invoke()
}