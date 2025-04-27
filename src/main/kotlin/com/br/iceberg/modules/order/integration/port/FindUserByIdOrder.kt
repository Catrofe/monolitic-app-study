package com.br.iceberg.modules.order.integration.port

import com.br.iceberg.modules.user.entity.UserEntity

interface FindUserByIdOrder {
    fun findUserById(id: Long): UserEntity?
}