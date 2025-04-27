package com.br.iceberg.integration.adapter

import com.br.iceberg.modules.cup.entity.CupEntity
import com.br.iceberg.modules.order.integration.port.CupIntegrationOrder as ValidCupExistsPortOrder
import com.br.iceberg.modules.cup.integration.port.ValidCupExists as ValidCupExistsPortCup
import org.springframework.stereotype.Service

@Service
class CupIntegrationOrderAdapter(
    private val validCupExists: ValidCupExistsPortCup,
) : ValidCupExistsPortOrder {
    override fun isValidCup(cup: Long): CupEntity? {
        return validCupExists.isValidCup(cup)
    }
}