package com.br.iceberg.modules.user.exception

abstract class UserDomainException(
    val code: String
) : RuntimeException() {
    open fun args(): Array<Any?> = emptyArray()
    open fun additionalInfo(): Map<String, String>? = null
}

class UserNotFoundException(private val user: String) : UserDomainException("USER-001") {
    override fun args(): Array<Any?> = arrayOf(user)
}

class UserAlreadyExistsException(private val user: String) : UserDomainException("USER-002") {
    override fun args(): Array<Any?> = arrayOf(user)
}

class UserNotAuthorizedException(private val user: String) : UserDomainException("USER-003") {
    override fun args(): Array<Any?> = arrayOf(user)
}

class UserBadRequestException(private val user: String) : UserDomainException("USER-004") {
    override fun args(): Array<Any?> = arrayOf(user)
}

class UserBadRequestUpdateException(private val user: String) : UserDomainException("USER-005") {
    override fun args(): Array<Any?> = arrayOf(user)
}