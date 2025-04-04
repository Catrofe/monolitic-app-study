package com.br.iceberg

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IcebergApiApplication

fun main(args: Array<String>) {
    runApplication<IcebergApiApplication>(*args)
}
