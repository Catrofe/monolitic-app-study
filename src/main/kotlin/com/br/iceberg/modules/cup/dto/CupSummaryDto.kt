package com.br.iceberg.modules.cup.dto

data class CupSummaryDto(
    val id: Long,
    val name: String,
    val description: String,
    val volumeMl: Double,
    val price: Int,
    val isAvailable: Boolean,
)
