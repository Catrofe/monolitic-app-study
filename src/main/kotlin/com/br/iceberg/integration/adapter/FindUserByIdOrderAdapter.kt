package com.br.iceberg.integration.adapter

import com.br.iceberg.modules.order.integration.port.FindUserByIdOrder
import com.br.iceberg.modules.user.entity.UserEntity
import com.br.iceberg.modules.user.integration.port.UserEntityProvider
import org.springframework.stereotype.Service

@Service
class FindUserByIdOrderAdapter(
    private val userEntityProvider: UserEntityProvider,
): FindUserByIdOrder {
    override fun findUserById(id: Long): UserEntity? {
        return userEntityProvider.findUserById(id)
    }
}