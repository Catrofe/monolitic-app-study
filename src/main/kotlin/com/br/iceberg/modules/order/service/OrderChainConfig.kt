package com.br.iceberg.modules.order.service

import com.br.iceberg.modules.order.service.orderProcessChain.ValidOrderCup
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OrderChainConfig {

    @Bean
    fun orderProcessingChain(
        validOrder: ValidOrderCup,
    ): OrderProcessingChain {
        return OrderProcessingChain(
            listOf(validOrder)
        )
    }
}