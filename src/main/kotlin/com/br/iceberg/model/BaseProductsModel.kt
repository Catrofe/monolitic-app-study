package com.br.iceberg.model

data class BaseProductsModel(
    val id: Long,
    val name: String,
    val description: String,
    val type: TypeProducts,
    val isPlusProduct: Boolean,
    val plusPrice: Int?,
    val isAvailable: Boolean ,
) {
}