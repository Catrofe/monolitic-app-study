package com.br.iceberg.modules.order.dto

import com.br.iceberg.model.PaymentType
import com.br.iceberg.modules.cup.entity.CupEntity
import com.br.iceberg.modules.user.entity.UserEntity

class OrderDraft(
    var user: UserEntity,
    var cupSize: CupEntity,
    var items: List<OrderItemDraft>,
    var totalPrice: Int = 0,
    var paymentType: PaymentType
)
