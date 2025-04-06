package com.br.iceberg.modules.user.dto

import com.br.iceberg.model.Role

data class UpdateRoleUser(
    val roles: Set<Role> = emptySet()
) {
}