package com.br.iceberg.modules.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UpdateUser(
    @field:NotBlank(message = "O primeiro nome e obrigatorio")
    @field:Size(min = 2, max = 50, message = "O primeiro nome deve ter entre 2 e 50 caracteres")
    val firstName: String,

    @field:NotBlank(message = "O sobrenome e obrigatorio")
    @field:Size(min = 2, max = 50, message = "O sobrenome deve ter entre 2 e 50 caracteres")
    val lastName: String,

    @field:NotBlank(message = "O email e obrigatorio")
    @field:Email(message = "Email invalido")
    val email: String,

    @field:NotBlank(message = "O telefone e obrigatorio")
    val phone: String,
)
