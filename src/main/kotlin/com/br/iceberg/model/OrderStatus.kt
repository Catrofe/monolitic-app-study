package com.br.iceberg.model

enum class OrderStatus(val description: String) {
    PENDING("Pedido foi criado e está aguardando processamento"),
    IN_PROGRESS("Pedido está sendo preparado na loja"),
    COMPLETED("Pedido foi concluído e está pronto para entrega"),
    CANCELED("Pedido foi cancelado pelo cliente ou loja"),
    DELIVERED("Pedido foi entregue ao cliente");

    fun getDescription(): String {
        return description
    }
}