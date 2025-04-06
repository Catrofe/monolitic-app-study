package com.br.iceberg.modules.user.entity

import com.br.iceberg.model.Role
import com.br.iceberg.model.UserModel
import com.br.iceberg.modules.user.dto.CreateNewUser
import com.br.iceberg.modules.user.dto.UpdateUser
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.Table

@Entity
@Table(
    indexes = [
        Index(name = "idx_user_email", columnList = "email"),
        Index(name = "idx_user_phone", columnList = "phone"),
    ]
)
data class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val firstName: String,

    @Column(nullable = false)
    val lastName: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false, unique = true, length = 16)
    val phone: String,

    @Column(nullable = false)
    val isBackoffice: Boolean,

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")]
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val roles: Set<Role> = emptySet()
){
    constructor(createNewUser: CreateNewUser, password: String) : this(
        id = 0,
        firstName = createNewUser.firstName,
        lastName = createNewUser.lastName,
        email = createNewUser.email,
        password = password,
        phone = createNewUser.phone,
        isBackoffice = false,
        roles = setOf(Role.USER),
    )

    fun toModel(): UserModel {
        return UserModel(
            id = id,
            firstName = firstName,
            lastName = lastName,
            email = email,
            phone = phone,
            isBackoffice = isBackoffice,
            roles = roles.map { it.name }.toSet()
        )
    }

    fun updateUser(user: UpdateUser): UserEntity {
        return this.copy(
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email,
            phone = user.phone,
        )
    }

    fun updatePassword(password: String): UserEntity {
        return this.copy(
            password = password
        )
    }
}
