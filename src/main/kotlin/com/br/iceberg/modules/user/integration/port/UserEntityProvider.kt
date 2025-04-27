package com.br.iceberg.modules.user.integration.port

import com.br.iceberg.modules.user.entity.UserEntity

interface UserEntityProvider {
    fun findUserById(id: Long): UserEntity?
}