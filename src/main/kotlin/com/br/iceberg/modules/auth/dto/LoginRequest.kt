package com.br.iceberg.modules.auth.dto

data class LoginRequest(
    val user: String,
    val password: String,
)
