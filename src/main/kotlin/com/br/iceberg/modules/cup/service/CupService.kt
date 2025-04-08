package com.br.iceberg.modules.cup.service

import com.br.iceberg.model.CupModel
import com.br.iceberg.model.PageResponse
import com.br.iceberg.modules.cup.dto.CupCreateDto
import com.br.iceberg.modules.cup.dto.CupUpdateDto
import com.br.iceberg.modules.cup.repository.CupRepository
import com.br.iceberg.modules.cup.entity.CupEntity
import com.br.iceberg.modules.cup.exception.CupNotFoundException
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CupService(
    val cupRepository: CupRepository,
) {
    @Transactional
    fun createCup(cup: CupCreateDto): CupModel {
        val cupEntity = CupEntity(cup)
        val savedCup = cupRepository.save(cupEntity)
        return savedCup.toModel()
    }

    @Transactional
    fun updateCup(cup: CupUpdateDto): CupModel {
        val cupEntity = cupRepository.findById(cup.id)
            .orElseThrow { CupNotFoundException(cup.id.toString()) }
        cupEntity.update(cup)
        return cupRepository.save(cupEntity).toModel()
    }

    @Transactional
    fun patchCup(id: Long): CupModel {
        val cupEntity = cupRepository.findById(id)
            .orElseThrow { CupNotFoundException(id.toString()) }
        cupEntity.changeAvailability()
        return cupRepository.save(cupEntity).toModel()
    }

    fun getCup(id: Long): CupModel {
        val cupEntity = cupRepository.findById(id)
            .orElseThrow { CupNotFoundException(id.toString()) }
        return cupEntity.toModel()
    }

    fun getAllCups(pageable: Pageable): PageResponse<CupModel> {
        val page = cupRepository.findAll(pageable).map { it.toModel() }
        return PageResponse(
            content = page.content,
            pageNumber = page.number,
            pageSize = page.size,
            totalElements = page.totalElements,
            totalPages = page.totalPages
        )
    }

    fun deleteCup(id: Long) {
        val cupEntity = cupRepository.findById(id)
            .orElseThrow { CupNotFoundException(id.toString()) }
        cupRepository.delete(cupEntity)
    }
}