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
class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(nullable = false)
    var firstName: String,

    @Column(nullable = false)
    var lastName: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false, unique = true, length = 16)
    var phone: String,

    @Column(nullable = false)
    var isBackoffice: Boolean = false,

    @Column(nullable = false, columnDefinition = "boolean default false")
    var isBlocked: Boolean = false,

    @ElementCollection(fetch = FetchType.EAGER, targetClass = Role::class)
    @CollectionTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")]
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    var roles: Set<Role> = emptySet()
){
    constructor(createNewUser: CreateNewUser, password: String) : this(
        id = 0,
        firstName = createNewUser.firstName,
        lastName = createNewUser.lastName,
        email = createNewUser.email,
        password = password,
        phone = createNewUser.phone,
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
            isBlocked = isBlocked,
            roles = roles.map { it.name }.toSet()
        )
    }

    fun updateUser(user: UpdateUser) {
        this.firstName = user.firstName
        this.lastName = user.lastName
        this.email = user.email
        this.phone = user.phone
    }

    fun updatePassword(password: String) {
        this.password = password
    }

    fun updateIsBackoffice(isBackoffice: Boolean) {
        this.isBackoffice = isBackoffice
    }

    fun updateIsBlocked(isBlocked: Boolean) {
        this.isBlocked = isBlocked
    }

    fun updateRoles(roles: Set<Role>) {
        this.roles = roles
    }
}
