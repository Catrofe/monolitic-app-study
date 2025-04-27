package com.br.iceberg.modules.order.service

import com.br.iceberg.modules.order.service.orderProcessChain.CalculateIndividualItems
import com.br.iceberg.modules.order.service.orderProcessChain.CalculateOrder
import com.br.iceberg.modules.order.service.orderProcessChain.ValidOrderErrors
import com.br.iceberg.modules.order.service.orderProcessChain.ValidOrderItems
import com.br.iceberg.modules.order.service.orderProcessChain.ValidOrderUser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OrderChainConfig {

    @Bean
    fun orderProcessingChain(
        validOrderItems: ValidOrderItems,
        validOrderUser: ValidOrderUser,
        validOrderErrors: ValidOrderErrors,
        calculateIndividualItems: CalculateIndividualItems,
        calculateOrder: CalculateOrder,
    ): OrderProcessingChain {
        return OrderProcessingChain(
            listOf(
                validOrderItems,
                validOrderUser,
                validOrderErrors,
                calculateIndividualItems,
                calculateOrder
            )
        )
    }
}