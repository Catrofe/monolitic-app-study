package com.br.iceberg.modules.cup.integration.adapter

import com.br.iceberg.modules.cup.integration.port.ValidCupExists
import com.br.iceberg.modules.cup.repository.CupRepository
import org.springframework.stereotype.Service

@Service
class ValidCupExistsImpl(
    private val cupRepository: CupRepository,
) : ValidCupExists {
    override fun isValidCup(cup: Long) =
        cupRepository.existsByIdAndAvailable(cup)
}