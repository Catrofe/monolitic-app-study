package com.br.iceberg.modules.cup.repository

import com.br.iceberg.modules.cup.entity.CupEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CupRepository : JpaRepository<CupEntity, Long> {
}