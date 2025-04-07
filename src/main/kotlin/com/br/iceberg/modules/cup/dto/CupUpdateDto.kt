package com.br.iceberg.modules.cup.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CupUpdateDto(

    @NotNull
    val id: Long,

    @NotBlank
    val name: String,

    @NotBlank
    val description: String,

    @NotNull
    val volumeMl: Double,

    @NotNull
    val price: Int,

    @NotNull
    val maxToppings: Int,

    @NotNull
    val maxSyrups: Int,

    @NotNull
    val allowExtraToppings: Boolean,

    @NotNull
    val allowExtraSyrups: Boolean,

    @NotNull
    val toppingExtraPrice: Int,

    @NotNull
    val syrupExtraPrice: Int,
)