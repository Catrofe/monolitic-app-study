package com.br.iceberg.modules.order.dto

import com.br.iceberg.model.PaymentType
import com.br.iceberg.modules.user.entity.UserEntity

class OrderDraft(
    var user: UserEntity? = null,
    var items: MutableList<OrderItemDraft> = mutableListOf(),
    var totalPrice: Int = 0,
    var paymentType: PaymentType
)
