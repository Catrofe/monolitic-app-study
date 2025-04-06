package com.br.iceberg.model

enum class Role(val description: String) {
    ADMIN("Administrador do sistema"),
    USER("Usuário padrão"),
    MANAGER("Gerente"),
    VENDOR("Vendedor");

    fun getAuthority(): String = "ROLE_$name"
}