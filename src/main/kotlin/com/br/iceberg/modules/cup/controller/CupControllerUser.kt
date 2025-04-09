package com.br.iceberg.modules.cup.controller

import com.br.iceberg.modules.cup.service.CupUsersService
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cup/user")
@PreAuthorize("isAuthenticated()")
class CupControllerUser(
    val cupService: CupUsersService
) {

    @GetMapping("/is-available")
    fun getAllCupsToUser(pageable: Pageable) = cupService.getAllCupsToUser(pageable)

    @GetMapping("/{id}")
    fun getCup(@PathVariable id: Long) = cupService.getCup(id)
}