package com.br.iceberg.modules.order.integration.port

import com.br.iceberg.modules.cup.entity.CupEntity

interface CupIntegrationOrder {
    fun isValidCup(cup: Long): CupEntity?
}