package com.br.iceberg.config.auth

import com.br.iceberg.modules.auth.exception.UnauthorizedIcebergException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")
        val token = authHeader?.replace("Bearer ", "")

        if (!token.isNullOrEmpty()) {
            try {
                val username = jwtTokenProvider.extractUsernameFromAccessToken(token)

                if (username.isNotEmpty() && SecurityContextHolder.getContext().authentication == null) {
                    val userDetails = userDetailsService.loadUserByUsername(username)

                    if (jwtTokenProvider.validateAccessToken(token, userDetails)) {
                        val authentication = UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.authorities
                        )
                        SecurityContextHolder.getContext().authentication = authentication
                    }
                }
            } catch (e: Exception) {
                logger.error("Invalid token", e)
                throw UnauthorizedIcebergException()
            }
        }
        filterChain.doFilter(request, response)
    }
}