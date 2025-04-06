package com.br.iceberg.modules.auth.exception

abstract class AuthDomainException(
    val code: String
) : RuntimeException() {
    open fun args(): Array<Any?> = emptyArray()
    open fun additionalInfo(): Map<String, String>? = null
}

class AuthUserNotFoundException(private val user: String) : AuthDomainException("AUTH-001") {
    override fun args(): Array<Any?> = arrayOf(user)
}

class AuthBadRequestLoginException(private val user: String) : AuthDomainException("AUTH-002") {
    override fun args(): Array<Any?> = arrayOf(user)
}

class UnauthorizedIcebergException : AuthDomainException("AUTH-003") {
    override fun args(): Array<Any?> = arrayOf()
}

