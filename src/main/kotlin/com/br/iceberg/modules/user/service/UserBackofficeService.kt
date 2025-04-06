package com.br.iceberg.modules.user.service

import com.br.iceberg.model.Role
import com.br.iceberg.model.UserModel
import com.br.iceberg.modules.user.exception.UserNotFoundException
import com.br.iceberg.modules.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserBackofficeService(
    private val userRepository: UserRepository,
) {

    @Transactional
    fun updateUserIsBackoffice(userIsBackoffice: Boolean, idUser: Long): UserModel {
        val userEntity = userRepository.findById(idUser)
            .orElseThrow { UserNotFoundException(idUser.toString()) }
        userEntity.updateIsBackoffice(userIsBackoffice)
        val userUpdated = userRepository.save(userEntity)
        return userUpdated.toModel()
    }

    @Transactional
    fun updateUserIsBlocked(userIsBlocked: Boolean,  idUser: Long): UserModel {
        val userEntity = userRepository.findById(idUser)
            .orElseThrow { UserNotFoundException(idUser.toString()) }
        userEntity.updateIsBlocked(userIsBlocked)
        val userUpdated = userRepository.save(userEntity)
        return userUpdated.toModel()
    }

    @Transactional
    fun updateUserRoles(roles: Set<Role>,  idUser: Long): UserModel {
        val userEntity = userRepository.findById(idUser)
            .orElseThrow { UserNotFoundException(idUser.toString()) }
        userEntity.updateRoles(roles)
        val userUpdated =  userRepository.save(userEntity)
        return userUpdated.toModel()
    }

}