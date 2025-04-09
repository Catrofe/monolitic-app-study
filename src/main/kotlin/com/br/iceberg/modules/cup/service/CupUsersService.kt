package com.br.iceberg.modules.cup.service

import com.br.iceberg.model.CupModel
import com.br.iceberg.model.PageResponse
import com.br.iceberg.modules.cup.dto.CupSummaryDto
import com.br.iceberg.modules.cup.exception.CupNotAvailableException
import com.br.iceberg.modules.cup.repository.CupRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CupUsersService(
    private val cupRepository: CupRepository,
) {
    fun getAllCupsToUser(pageable: Pageable): PageResponse<CupSummaryDto> {
        val cups = cupRepository.findAllByIsAvailableTrue(pageable).map { it.toSummaryModel() }
        return PageResponse(
            content = cups.content,
            pageNumber = cups.number,
            pageSize = cups.size,
            totalElements = cups.totalElements,
            totalPages = cups.totalPages
        )
    }

    fun getCup(id: Long): CupModel {
        return cupRepository.findByIdAndIsAvailableTrue(id)?.toModel()
            ?: throw CupNotAvailableException("$id")
    }
}