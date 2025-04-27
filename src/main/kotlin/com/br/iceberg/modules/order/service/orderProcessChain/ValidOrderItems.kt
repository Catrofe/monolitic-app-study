package com.br.iceberg.modules.order.service.orderProcessChain

import com.br.iceberg.modules.cup.entity.CupEntity
import com.br.iceberg.modules.order.dto.OrderItemDraft
import com.br.iceberg.modules.order.dto.OrderProcessingContext
import com.br.iceberg.modules.order.integration.port.CupIntegrationOrder
import com.br.iceberg.modules.order.integration.port.ProductIntegrationOrder
import com.br.iceberg.modules.product.entity.BaseProductsEntity
import org.springframework.stereotype.Service

@Service
class ValidOrderItems(
    private val cupIntegrationOrder: CupIntegrationOrder,
    private val productIntegrationOrder: ProductIntegrationOrder,
): OrderHandlerChain {
    override fun handle(context: OrderProcessingContext): OrderProcessingContext {
        context.request.items.forEach { item ->
            val cup = cupIntegrationOrder.isValidCup(item.cupSizeId)
            val product = productIntegrationOrder.isValidProduct(item.baseProductId)
            val toppings = validAddons(
                productIntegrationOrder.getToppingsOrSyrups(item.toppings), item.toppings, context
            )
            val syrups = validAddons(productIntegrationOrder.getToppingsOrSyrups(item.syrups), item.syrups, context)
            val plusItems = validAddons(productIntegrationOrder.getPlusItems(item.plusItems), item.plusItems, context)

            if (cup == null) {
                context.errors.add("Tamanho de copo invalido: ${item.cupSizeId}")
            } else if (product == null) {
                context.errors.add("Produto ${item.baseProductId} invalido.")
            } else {
                validQuantityAddons(cup, toppings, syrups, context)
                context.draft.items.add(
                    OrderItemDraft(
                        baseProduct = product,
                        cupSize = cup,
                        quantity = item.quantity,
                        toppings = toppings,
                        syrups = syrups,
                        plusItems = plusItems,
                    )
                )
            }
        }
        return context
    }

    private fun validAddons(
        addonsEntity: List<BaseProductsEntity>,
        addonsIds: List<Long>,
        context: OrderProcessingContext
    ): List<BaseProductsEntity> {
        for (addon in addonsIds) {
            val addonEntity = addonsEntity.find { it.id == addon }
            if (addonEntity == null) {
                context.errors.add("Produto $addon invalido.")
            }
        }
        return addonsEntity
    }

    private fun validQuantityAddons(
        cup: CupEntity,
        toppings: List<BaseProductsEntity>,
        syrups: List<BaseProductsEntity>,
        context: OrderProcessingContext
    ) {
        if (toppings.size > cup.maxToppings && !cup.allowExtraToppings) {
            context.errors.add("Quantidade de toppings invalida: ${cup.maxToppings}")
        }
        if (syrups.size > cup.maxSyrups && !cup.allowExtraSyrups) {
            context.errors.add("Quantidade de syrups invalida: ${cup.maxSyrups}")
        }
    }
}
