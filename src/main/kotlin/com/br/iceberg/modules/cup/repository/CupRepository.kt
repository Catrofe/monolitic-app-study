package com.br.iceberg.modules.cup.repository

import com.br.iceberg.modules.cup.entity.CupEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CupRepository : JpaRepository<CupEntity, Long> {

    @Query("SELECT c FROM CupEntity c WHERE c.isAvailable = true")
    fun findAllByIsAvailableTrue(pageable: Pageable): Page<CupEntity>

    @Query("SELECT c FROM CupEntity c WHERE c.id = :id AND c.isAvailable = true")
    fun findByIdAndIsAvailableTrue(id: Long): CupEntity?
    fun existsByIdAndAvailable(cup: Long, available: Boolean = true): Boolean
}