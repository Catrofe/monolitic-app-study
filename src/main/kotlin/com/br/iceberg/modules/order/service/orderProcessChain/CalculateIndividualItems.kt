package com.br.iceberg.modules.order.service.orderProcessChain

import com.br.iceberg.modules.order.dto.OrderProcessingContext
import com.br.iceberg.modules.product.entity.BaseProductsEntity
import org.springframework.stereotype.Service

@Service
class CalculateIndividualItems: OrderHandlerChain {
    override fun handle(context: OrderProcessingContext): OrderProcessingContext {
        for (item in context.draft.items) {
            val cupPrice = item.cupSize.price
            val toppingsPrice = calculateAddonsExtraPrice(
                item.cupSize.toppingExtraPrice, item.cupSize.maxToppings, item.toppings
            )
            val syrupsPrice = calculateAddonsExtraPrice(
                item.cupSize.syrupExtraPrice, item.cupSize.maxSyrups, item.syrups
            )
            val plusItemsPrice = calculatePlusAddonsPrice(item.plusItems)

            item.calculatedPrice = ((cupPrice + toppingsPrice + syrupsPrice + plusItemsPrice) * item.quantity)
        }
        return context
    }

    private fun calculateAddonsExtraPrice(
        cupExtraPrice: Int,
        cupLimitItemsDefault: Int,
        items: List<BaseProductsEntity>
    ): Int {
        if (items.size < cupLimitItemsDefault) {
            return 0
        }
        val extraItems = items.size - cupLimitItemsDefault
        val extraItemsPrice = extraItems * cupExtraPrice
        return extraItemsPrice
    }

    private fun calculatePlusAddonsPrice(
        items: List<BaseProductsEntity>
    ): Int {
        return items.sumOf { it.plusPrice ?: 0 }
    }
}