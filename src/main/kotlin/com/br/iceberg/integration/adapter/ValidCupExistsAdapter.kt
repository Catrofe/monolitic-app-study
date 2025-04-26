package com.br.iceberg.integration.adapter

import com.br.iceberg.modules.order.integration.port.ValidCupExists as ValidCupExistsPortOrder
import com.br.iceberg.modules.cup.integration.port.ValidCupExists as ValidCupExistsPortCup
import org.springframework.stereotype.Service

@Service
class ValidCupExistsAdapter(
    private val validCupExists: ValidCupExistsPortCup,
) : ValidCupExistsPortOrder {
    override fun isValidCup(cup: Long): Boolean {
        return validCupExists.isValidCup(cup)
    }
}