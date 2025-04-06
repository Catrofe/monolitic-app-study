package com.br.iceberg.modules.user.controller

import com.br.iceberg.model.UserModel
import com.br.iceberg.modules.user.dto.UpdateRoleUser
import com.br.iceberg.modules.user.entity.UserDetailsImpl
import com.br.iceberg.modules.user.exception.UserNotAuthorizedException
import com.br.iceberg.modules.user.service.UserBackofficeService
import jakarta.validation.Valid
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user/backoffice")
class UserBackofficeController(
    private val userBackofficeService: UserBackofficeService,
) {

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun updateUserIsBackoffice(
        @PathVariable id: Long,
        @RequestParam userIsBackoffice: Boolean,
        @AuthenticationPrincipal userLogged: UserDetailsImpl
    ): UserModel {
        if (userLogged.getId() == id) {
            throw UserNotAuthorizedException(userLogged.getId().toString())
        }
        return userBackofficeService.updateUserIsBackoffice(userIsBackoffice, id)

    }

    @PatchMapping("/{id}/blocked")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    fun updateUserIsBlocked(
        @PathVariable id: Long,
        @RequestParam userIsBlocked: Boolean,
        @AuthenticationPrincipal userLogged: UserDetailsImpl
    ): UserModel {
        if (userLogged.getId() == id) {
            throw UserNotAuthorizedException(userLogged.getId().toString())
        }
        return userBackofficeService.updateUserIsBlocked(userIsBlocked, id)
    }

    @PatchMapping("/{id}/role")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun updateUserRoles(
        @PathVariable id: Long,
        @Valid @RequestBody  roles: UpdateRoleUser,
        @AuthenticationPrincipal userLogged: UserDetailsImpl
    ): UserModel {
        if (userLogged.getId() == id) {
            throw UserNotAuthorizedException(userLogged.getId().toString())
        }
        return userBackofficeService.updateUserRoles(roles.roles, id)
    }


}