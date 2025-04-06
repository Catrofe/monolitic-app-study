package com.br.iceberg.modules.user.service

import com.br.iceberg.model.Role
import com.br.iceberg.model.UserModel
import com.br.iceberg.modules.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserBackofficeService(
    private val userRepository: UserRepository,
) {

    @Transactional
    fun updateUserIsBackoffice(userIsBackoffice: Boolean, idUser: Long): UserModel =
    userRepository.findById(idUser)
        .map { userRepository.save(it.updateIsBackoffice(userIsBackoffice)).toModel() }
        .orElseThrow { IllegalArgumentException("Usuário não encontrado") }


    @Transactional
    fun updateUserIsBlocked(userIsBlocked: Boolean,  idUser: Long): UserModel =
    userRepository.findById(idUser)
        .map { userRepository.save(it.updateIsBlocked(userIsBlocked)).toModel() }
        .orElseThrow {
            IllegalArgumentException("Usuário não encontrado")
        }


    @Transactional
    fun updateUserRoles(roles: Set<Role>,  idUser: Long): UserModel =
    userRepository.findById(idUser)
        .map { userRepository.save(it.updateRoles(roles)).toModel() }
        .orElseThrow { IllegalArgumentException("Usuário não encontrado") }

}