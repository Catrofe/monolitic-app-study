package com.br.iceberg.modules.product.controller

import com.br.iceberg.model.TypeProducts
import com.br.iceberg.modules.product.service.ProductsUserService
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product/user")
@PreAuthorize("isAuthenticated()")
class ProductsUserController(
    val service: ProductsUserService,
) {

    @GetMapping("/is-available")
    fun getAllProductsToUser(
        @RequestParam(required = false) type: TypeProducts?,
        @RequestParam(required = false) isPlusProduct: Boolean?,
        pageable: Pageable
    ) = service.getAllProductsToUser(type, isPlusProduct, pageable)

}