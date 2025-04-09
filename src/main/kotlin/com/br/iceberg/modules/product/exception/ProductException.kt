package com.br.iceberg.modules.product.exception


abstract class ProductDomainException(
    val code: String
) : RuntimeException() {
    open fun args(): Array<Any?> = emptyArray()
    open fun additionalInfo(): Map<String, String>? = null
}

class ProductNotFoundException(private val product: String) : ProductDomainException("PRODUCT-001") {
    override fun args(): Array<Any?> = arrayOf(product)
}

class ProductPlusCreateException: ProductDomainException("PRODUCT-002") {
    override fun args(): Array<Any?> = arrayOf()
}

class ProductNotAvailableException(private val product: String) : ProductDomainException("PRODUCT-003") {
    override fun args(): Array<Any?> = arrayOf(product)
}