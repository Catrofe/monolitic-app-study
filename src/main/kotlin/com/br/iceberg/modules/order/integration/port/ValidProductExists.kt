package com.br.iceberg.modules.order.integration.port

interface ValidProductExists {
    fun isValidCup(cup: Long): Boolean
}