package com.br.iceberg.modules.cup.integration.port

interface ValidCupExists {
    fun isValidCup(cup: Long): Boolean
}