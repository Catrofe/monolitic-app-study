package com.br.iceberg.model

data class OrderItemModel(
    val baseProductId: Long,
    val baseProductName: String,
    val quantity: Int,
    val toppings: List<String>,
    val syrups: List<String>,
    val plusItems: List<String>
) {
}