package com.br.iceberg.modules.user.integration.adapter

import com.br.iceberg.modules.user.entity.UserEntity
import com.br.iceberg.modules.user.integration.port.UserEntityProvider
import com.br.iceberg.modules.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserEntityProviderImpl(
    private val userRepository: UserRepository
): UserEntityProvider {
    override fun findUserById(id: Long): UserEntity? {
        return userRepository.findByIdAndIsBlockedFalse(id)
    }
}