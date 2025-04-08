package com.br.iceberg.modules.user.dto

import jakarta.validation.constraints.NotBlank

data class UpdatePassword(

    @field:NotBlank
    val oldPassword: String,
    @field:NotBlank
    val newPassword: String,
)
