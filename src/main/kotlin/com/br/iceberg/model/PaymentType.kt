package com.br.iceberg.model

enum class PaymentType(val description: String) {
    CASH("Dinheiro"),
    CREDIT_CARD("Cartão de Crédito"),
    DEBIT_CARD("Cartão de Débito"),
    PIX("Pix"),
    VOUCHER("Vale Refeição");

    fun getDescription(): String {
        return description
    }
}