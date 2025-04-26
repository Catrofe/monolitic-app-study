package com.br.iceberg.modules.order.integration.port

interface ValidCupExists {
    fun isValidCup(cup: Long): Boolean
}