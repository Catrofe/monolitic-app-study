package com.br.iceberg.modules.user.dto

data class UpdatePassword(
    val oldPassword: String,
    val newPassword: String,
)
