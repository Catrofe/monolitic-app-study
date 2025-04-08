package com.br.iceberg.modules.cup.controller

import com.br.iceberg.modules.cup.dto.CupCreateDto
import com.br.iceberg.modules.cup.dto.CupUpdateDto
import com.br.iceberg.modules.cup.service.CupService
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cup")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
class CupController(
    val cupService: CupService,
) {

    @PostMapping
    fun createCup(@RequestBody @Valid cup: CupCreateDto) =  cupService.createCup(cup)
    

    @PutMapping
    fun updateCup(@Valid @RequestBody cup: CupUpdateDto) =  cupService.updateCup(cup)
    

    @PatchMapping("/{id}")
    fun updateCupAvailability(@PathVariable id: Long) =  cupService.patchCup(id)
    

    @GetMapping("/{id}")
    fun getCup(@PathVariable id: Long) =  cupService.getCup(id)
    

    @GetMapping
    fun getAllCups(pageable: Pageable) =  cupService.getAllCups(pageable)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCup(@PathVariable id: Long) =  cupService.deleteCup(id)
}
