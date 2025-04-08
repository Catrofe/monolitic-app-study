package com.br.iceberg.modules.product.repository

import com.br.iceberg.modules.product.entity.BaseProductsEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BaseProductsRepository: JpaRepository<BaseProductsEntity, Long>