package com.br.iceberg.modules.user.dto

import com.br.iceberg.model.Role
import jakarta.validation.constraints.NotNull


data class UpdateRoleUser(
    @field:NotNull
    val roles: Set<Role> = emptySet()
) {
}