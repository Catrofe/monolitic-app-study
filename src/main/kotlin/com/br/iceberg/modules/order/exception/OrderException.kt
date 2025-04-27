package com.br.iceberg.modules.order.exception


abstract class OrderDomainException(
    val code: String
) : RuntimeException() {
    open fun args(): Array<Any?> = emptyArray()
    open fun additionalInfo(): Map<String, String>? = null
}


class UnableProcessOrder(private val errors: Array<Any?>) : OrderDomainException("ORDER-001") {
    override fun args(): Array<Any?> = errors
}