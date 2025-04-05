package com.br.iceberg.modules.auth.dto

import com.br.iceberg.model.Role

data class UserLoginDto(
    val id: Long,
    val email: String,
    val password: String,
    val roles: Set<Role> = emptySet()
)
