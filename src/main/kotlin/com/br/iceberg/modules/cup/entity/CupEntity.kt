package com.br.iceberg.modules.cup.entity

import com.br.iceberg.model.CupModel
import com.br.iceberg.modules.cup.dto.CupCreateDto
import com.br.iceberg.modules.cup.dto.CupUpdateDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
@Suppress("LongParameterList")
class CupEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(length = 64, nullable = false)
    var name: String,

    @Column(length = 255, nullable = false)
    var description: String,

    @Column(nullable = false)
    var volumeMl: Double,

    @Column(nullable = false)
    var price: Int,

    @Column(nullable = false)
    var maxToppings: Int,

    @Column(nullable = false)
    var maxSyrups: Int,

    @Column(nullable = false)
    var allowExtraToppings: Boolean,

    @Column(nullable = false)
    var allowExtraSyrups: Boolean,

    @Column(nullable = false)
    var toppingExtraPrice: Int,

    @Column(nullable = false)
    var syrupExtraPrice: Int,

    @Column(nullable = false)
    var isAvailable: Boolean,
) {

    constructor(cup: CupCreateDto): this (
        id = 0,
        name = cup.name,
        description = cup.description,
        volumeMl = cup.volumeMl,
        price = cup.price,
        maxToppings = cup.maxToppings,
        maxSyrups = cup.maxSyrups,
        allowExtraToppings = cup.allowExtraToppings,
        allowExtraSyrups = cup.allowExtraSyrups,
        toppingExtraPrice = cup.toppingExtraPrice,
        syrupExtraPrice = cup.syrupExtraPrice,
        isAvailable = false
    )

    fun update(cup: CupUpdateDto) {
        name = cup.name
        description = cup.description
        volumeMl = cup.volumeMl
        price = cup.price
        maxToppings = cup.maxToppings
        maxSyrups = cup.maxSyrups
        allowExtraToppings = cup.allowExtraToppings
        allowExtraSyrups = cup.allowExtraSyrups
        toppingExtraPrice = cup.toppingExtraPrice
        syrupExtraPrice = cup.syrupExtraPrice
    }

    fun changeAvailability() {
        isAvailable = !isAvailable
    }

    fun toModel(): CupModel {
        return CupModel(
            id = id,
            name = name,
            description = description,
            volumeMl = volumeMl,
            price = price,
            maxToppings = maxToppings,
            maxSyrups = maxSyrups,
            allowExtraToppings = allowExtraToppings,
            allowExtraSyrups = allowExtraSyrups,
            toppingExtraPrice = toppingExtraPrice,
            syrupExtraPrice = syrupExtraPrice,
            isAvailable = isAvailable
        )
    }
}
