package com.br.iceberg.model

data class CupModel(
    val id: Long,
    val name: String,
    val description: String,
    val volumeMl: Double,
    val price: Int,
    val maxToppings: Int,
    val maxSyrups: Int,
    val allowExtraToppings: Boolean,
    val allowExtraSyrups: Boolean,
    val toppingExtraPrice: Int,
    val syrupExtraPrice: Int,
    val isAvailable: Boolean
)