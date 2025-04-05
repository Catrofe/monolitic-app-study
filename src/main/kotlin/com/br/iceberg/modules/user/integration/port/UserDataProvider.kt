package com.br.iceberg.modules.user.integration.port

import com.br.iceberg.modules.user.entity.UserEntity

interface UserDataProvider {
    suspend fun findUserByEmail(email: String): UserEntity?
    suspend fun findUserByLogin(login: String): UserEntity?
}