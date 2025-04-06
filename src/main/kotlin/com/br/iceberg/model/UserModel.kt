package com.br.iceberg.model

data class UserModel(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val isBackoffice: Boolean,
    val isBlocked: Boolean,
    val roles: Set<String> = emptySet(),
)