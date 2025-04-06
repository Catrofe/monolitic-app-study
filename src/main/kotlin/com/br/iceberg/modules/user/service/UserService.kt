package com.br.iceberg.modules.user.service

import com.br.iceberg.model.UserModel
import com.br.iceberg.modules.user.dto.CreateNewUser
import com.br.iceberg.modules.user.dto.UpdatePassword
import com.br.iceberg.modules.user.dto.UpdateUser
import com.br.iceberg.modules.user.entity.UserEntity
import com.br.iceberg.modules.user.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    private val logger = LoggerFactory.getLogger(UserService::class.java)


    suspend fun createUser(user: CreateNewUser): UserModel {
        logger.info("Creating user with email: ${user.email} and phone: ${user.phone}")

        if (validateUser(user)) {
            logger.warn("User with email: ${user.email} and phone: ${user.phone} already exists, not creating")
            throw IllegalArgumentException("User with this email and phone already exists")
            // TODO: Create a custom exception
        }

        val newUser = withContext(Dispatchers.IO) {
            userRepository.save(UserEntity(user, passwordEncoder.encode(user.password)))
        }

        logger.info("User created with ID: ${newUser.id} and email: ${newUser.email}")
        return newUser.toModel()
    }

    private suspend fun validateUser(user: CreateNewUser): Boolean {
        return withContext(Dispatchers.IO) {
            userRepository.existsByEmailAndPhone(user.email, user.phone)
        }
    }

    suspend fun findUserById(id: Long): UserModel {
        return withContext(Dispatchers.IO) {
            val user = userRepository.findById(id).orElseThrow { IllegalArgumentException("User not found") }
            logger.info("User found with ID: ${user.id} and email: ${user.email}")
            user.toModel()
        }
    }

    fun findUserByEmail(userEmail: String?): UserModel? {
        return userEmail?.let {
            val user = userRepository.findByEmail(it)
            logger.info("User found with email: ${user?.email}")
            user?.toModel()
        }
    }

    @Transactional
    fun updateUser(user: UpdateUser, currentUserEmail: String): UserModel {
        val userEntity = userRepository.findByEmail(currentUserEmail)
            ?: throw IllegalArgumentException("Usuário não encontrado")

        validateUserUpdate(user, userEntity.id)

        val updatedUser = userRepository.save(userEntity.updateUser(user))

        logger.info("Usuário atualizado com ID: ${updatedUser.id} e email: ${updatedUser.email}")
        return updatedUser.toModel()
    }

    private fun validateUserUpdate(user: UpdateUser, id: Long) {
        userRepository.verifyIfUpdateUserIsSafe(user.email, user.phone, id).let {
            logger.warn("User with email: ${user.email} and phone: ${user.phone} already exists, not updating")
            "User with this email and phone already exists"
        }
    }

    @Transactional
    fun updatePassword(passwords: UpdatePassword, userEmail: String): UserModel {
        val userEntity = userRepository.findByEmail(userEmail)
            ?: throw IllegalArgumentException("User Not Found")

        if (!passwordEncoder.matches(passwords.oldPassword, userEntity.password)) {
            logger.warn("Old password does not match for user with email: ${userEntity.email}")
            throw IllegalArgumentException("Current password incorrect")
        }

        val updatedUser = userRepository.save(userEntity.updatePassword(passwordEncoder.encode(passwords.newPassword)))

        logger.info("Password updated to ID: ${updatedUser.id} and email: ${updatedUser.email}")
        return updatedUser.toModel()
    }
}
