package com.br.iceberg.modules.auth.dto

import com.br.iceberg.model.Role


data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val userId: Long,
    val userEmail: String,
    val roles: Set<Role>,
    val accessTokenExpiresIn: Long,
    val refreshTokenExpiresIn: Long,
)