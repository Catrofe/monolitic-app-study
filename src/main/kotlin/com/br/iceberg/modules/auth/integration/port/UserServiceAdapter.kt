package com.br.iceberg.modules.auth.integration.port

import com.br.iceberg.modules.auth.dto.UserLoginDto

interface UserServiceAdapter {
    suspend fun findUserToLogin(user: String): UserLoginDto?
    suspend fun findUserByEmail(email: String): UserLoginDto?
}