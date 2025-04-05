package com.br.iceberg.integration.adapter

import com.br.iceberg.modules.auth.dto.UserLoginDto
import com.br.iceberg.modules.auth.integration.port.UserServiceAdapter
import com.br.iceberg.modules.user.entity.UserEntity
import org.springframework.stereotype.Component
import com.br.iceberg.modules.user.integration.port.UserDataProvider

@Component
class UserIntegrationAdapter(
    private val userDataProvider: UserDataProvider
) : UserServiceAdapter {
    override suspend fun findUserToLogin(user: String): UserLoginDto? {
        return userDataProvider.findUserByLogin(user)?.toUserLoginDto()
    }

    override suspend fun findUserByEmail(email: String): UserLoginDto? {
        return userDataProvider.findUserByEmail(email)?.toUserLoginDto()
    }

    private fun UserEntity.toUserLoginDto() = UserLoginDto(
        id = this.id,
        email = this.email,
        password = this.password,
        roles = this.roles,
    )
}