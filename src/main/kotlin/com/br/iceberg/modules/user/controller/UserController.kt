package com.br.iceberg.modules.user.controller

import com.br.iceberg.model.UserModel
import com.br.iceberg.modules.user.dto.CreateNewUser
import com.br.iceberg.modules.user.dto.UpdatePassword
import com.br.iceberg.modules.user.dto.UpdateUser
import com.br.iceberg.modules.user.entity.UserDetailsImpl
import com.br.iceberg.modules.user.exception.UserNotFoundException
import com.br.iceberg.modules.user.service.UserService
import jakarta.validation.Valid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.nio.file.attribute.UserPrincipalNotFoundException
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/public")
    fun createUser(@Valid @RequestBody user: CreateNewUser): CompletableFuture<UserModel> {
        return CoroutineScope(Dispatchers.IO).future {
            userService.createUser(user)
        }
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    fun findCurrentUser(@AuthenticationPrincipal userLogged: UserDetailsImpl): UserModel {
        return userService.findUserByEmail(userLogged.getUsername())
                ?: throw UserNotFoundException(userLogged.getId().toString())
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    fun updateCurrentUser(
        @Valid @RequestBody user: UpdateUser,
        @AuthenticationPrincipal userLogged: UserDetailsImpl
    ): UserModel {
        return userService.updateUser(user, userLogged.getUsername())
    }

    @PatchMapping
    @PreAuthorize("isAuthenticated()")
    fun updatePasswordCurrentUser(
        @Valid @RequestBody passwords: UpdatePassword,
        @AuthenticationPrincipal userLogged: UserDetailsImpl
    ): UserModel {
        return userService.updatePassword(passwords, userLogged.getUsername())
    }
}
