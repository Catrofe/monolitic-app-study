package com.br.iceberg.model

enum class PaymentType(val description: String) {
    CASH("Dinheiro"),
    CREDIT_CARD("Cartao de Credito"),
    DEBIT_CARD("Cartao de Debito"),
    PIX("Pix"),
    VOUCHER("Vale Refeicao");
}
