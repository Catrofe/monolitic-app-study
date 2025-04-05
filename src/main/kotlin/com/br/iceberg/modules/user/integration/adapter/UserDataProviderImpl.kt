package com.br.iceberg.modules.user.integration.adapter

import com.br.iceberg.modules.user.entity.UserEntity
import com.br.iceberg.modules.user.integration.port.UserDataProvider
import com.br.iceberg.modules.user.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class UserDataProviderImpl(
    private val userRepository: UserRepository,
) : UserDataProvider {
    override suspend fun findUserByEmail(email: String): UserEntity? {
        return withContext(Dispatchers.IO) {
            userRepository.findByEmail(email)
        }
    }

    override suspend fun findUserByLogin(login: String): UserEntity? {
        return withContext(Dispatchers.IO) {
            userRepository.findByEmailOrPhone(login)
        }
    }
}
