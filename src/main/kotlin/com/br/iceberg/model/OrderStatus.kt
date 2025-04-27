package com.br.iceberg.model

enum class OrderStatus(val description: String) {
    PENDING("Pedido foi criado e esta aguardando processamento"),
    IN_PROGRESS("Pedido esta sendo preparado na loja"),
    COMPLETED("Pedido foi concluido e esta pronto para entrega"),
    CANCELED("Pedido foi cancelado pelo cliente ou loja"),
    DELIVERED("Pedido foi entregue ao cliente");
}
