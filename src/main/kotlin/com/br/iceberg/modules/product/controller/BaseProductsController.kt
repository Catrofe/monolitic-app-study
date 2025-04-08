package com.br.iceberg.modules.product.controller

import com.br.iceberg.modules.product.dto.CreateNewProduct
import com.br.iceberg.modules.product.dto.UpdateProductDto
import com.br.iceberg.modules.product.service.BaseProductsService
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
class BaseProductsController(
    val baseProductsService: BaseProductsService,
) {

    @PostMapping
    fun createNewProduct(@RequestBody @Valid product: CreateNewProduct) = baseProductsService.createNewProduct(product)

    @PutMapping
    fun updateProduct(@RequestBody @Valid product: UpdateProductDto) = baseProductsService.updateProduct(product)

    @PatchMapping("/{id}")
    fun updateProductAvailability(@PathVariable id: Long) = baseProductsService.updateProductAvailability(id)

    @GetMapping
    fun getAllProducts(pageable: Pageable) = baseProductsService.getAllProducts(pageable)

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long) = baseProductsService.getProductById(id)

    @DeleteMapping
    fun deleteProduct(@PathVariable id: Long) = baseProductsService.deleteProduct(id)
}