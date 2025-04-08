package com.br.iceberg.modules.auth.controller

import com.br.iceberg.modules.auth.dto.LoginRequest
import com.br.iceberg.modules.auth.dto.TokenResponse
import com.br.iceberg.modules.auth.service.AuthService
import jakarta.validation.Valid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
) {

     @PostMapping("/login")
     fun login(@Valid @RequestBody loginRequest: LoginRequest): CompletableFuture<TokenResponse> {
         return CoroutineScope(Dispatchers.IO).future {
             authService.login(loginRequest)
         }
     }

     @PostMapping("/refresh")
     fun refresh(@RequestHeader refreshToken: String): CompletableFuture<TokenResponse> {
         return CoroutineScope(Dispatchers.IO).future {
             authService.refresh(refreshToken)
         }
     }
}