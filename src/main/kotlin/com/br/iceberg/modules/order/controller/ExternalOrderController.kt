package com.br.iceberg.modules.order.controller

import com.br.iceberg.modules.order.dto.CreateOrderDTO
import com.br.iceberg.modules.order.entity.OrderItemEntity
import com.br.iceberg.modules.order.service.ExternalOrderService
import com.br.iceberg.modules.user.entity.UserDetailsImpl
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/external/order")
@PreAuthorize("isAuthenticated()")
class ExternalOrderController(
    val externalOrderService: ExternalOrderService,
) {

    @PostMapping
    fun createOrder(
        @RequestBody order: CreateOrderDTO,
        @AuthenticationPrincipal userLogged: UserDetailsImpl
    ) = externalOrderService.createOrder(order, userLogged.getId())

}