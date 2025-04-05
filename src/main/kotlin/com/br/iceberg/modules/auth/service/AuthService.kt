package com.br.iceberg.modules.auth.service

import com.br.iceberg.config.auth.JwtTokenProvider
import com.br.iceberg.modules.auth.dto.LoginRequest
import com.br.iceberg.modules.auth.dto.TokenResponse
import com.br.iceberg.modules.auth.integration.port.UserServiceAdapter
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwtUtils: JwtTokenProvider,
    private val userServiceAdapter: UserServiceAdapter,
    private val passwordEncoder: PasswordEncoder,
) {
    suspend fun login(loginRequest: LoginRequest): TokenResponse {
        val user = userServiceAdapter.findUserToLogin(loginRequest.user)
            ?: throw UsernameNotFoundException("User not found with user: ${loginRequest.user}")

        if (!validPassword(loginRequest.password, user.password)) {
            throw IllegalArgumentException("Invalid password")
        }

        return jwtUtils.generateTokensLogin(user)
    }

    private fun validPassword(passwordInput: String, passwordDb: String): Boolean {
        return passwordEncoder.matches(passwordInput, passwordDb)
    }

    suspend fun refresh(refreshToken: String): TokenResponse {
        if (!jwtUtils.validateRefreshToken(refreshToken)) {
            throw IllegalArgumentException("Token de refresh inválido ou expirado")
        }

        val email = jwtUtils.extractUsernameFromRefreshToken(refreshToken)

        val user = userServiceAdapter.findUserByEmail(email)
            ?: throw UsernameNotFoundException("Usuário não encontrado com email: $email")

        return jwtUtils.generateTokensLogin(user)
    }
}