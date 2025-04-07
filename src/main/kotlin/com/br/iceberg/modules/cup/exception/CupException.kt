package com.br.iceberg.modules.cup.exception


abstract class CupDomainException(
    val code: String
) : RuntimeException() {
    open fun args(): Array<Any?> = emptyArray()
    open fun additionalInfo(): Map<String, String>? = null
}

class CupNotFoundException(private val cup: String) : CupDomainException("CUP-001") {
    override fun args(): Array<Any?> = arrayOf(cup)
}