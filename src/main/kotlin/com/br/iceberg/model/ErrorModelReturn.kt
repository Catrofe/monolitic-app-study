package com.br.iceberg.model

data class ErrorModelReturn(
    val code: String,
    val message: String,
    val args: Array<Any?> = emptyArray(),
    val additionalInfo: Map<String, String>? = null,
) {
}