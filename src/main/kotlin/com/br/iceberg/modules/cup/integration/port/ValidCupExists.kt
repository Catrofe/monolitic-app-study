package com.br.iceberg.modules.cup.integration.port

import com.br.iceberg.modules.cup.entity.CupEntity

interface ValidCupExists {
    fun isValidCup(cup: Long): CupEntity?
}