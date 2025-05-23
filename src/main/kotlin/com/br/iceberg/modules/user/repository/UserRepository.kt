package com.br.iceberg.modules.user.repository

import com.br.iceberg.modules.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
    fun existsByEmailAndPhone(email: String, phone: String): Boolean

    @Query("SELECT u FROM UserEntity u WHERE (u.email = :login OR u.phone = :login) AND u.isBlocked = false")
    fun findByEmailOrPhone(login: String): UserEntity?

    @Query("SELECT u FROM UserEntity u WHERE (u.email = :login OR u.phone = :phone) AND u.id <> :id")
    fun verifyIfUpdateUserIsSafe(login: String, phone: String, id: Long): UserEntity?

    fun findByIdAndIsBlockedFalse(id: Long): UserEntity?
}