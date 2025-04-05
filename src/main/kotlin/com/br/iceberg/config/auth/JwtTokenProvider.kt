package com.br.iceberg.config.auth

import com.br.iceberg.modules.auth.dto.TokenResponse
import com.br.iceberg.modules.auth.dto.UserLoginDto
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtTokenProvider {

    @Value("\${jwt.access.secret}")
    private lateinit var accessSecret: String

    @Value("\${jwt.refresh.secret}")
    private lateinit var refreshSecret: String

    @Value("\${jwt.access.expiration:3600000}") // 1 hora em milissegundos
    private var accessTokenExpiration: Long = 0

    @Value("\${jwt.refresh.expiration:86400000}") // 24 horas em milissegundos
    private var refreshTokenExpiration: Long = 0

    suspend fun generateTokensLogin(user: UserLoginDto): TokenResponse {
        val accessToken = generateAccessToken(user)
        val refreshToken = generateRefreshToken(user.email)

        return TokenResponse(
            accessToken = accessToken,
            refreshToken = refreshToken,
            userId = user.id,
            userEmail = user.email,
            roles = user.roles,
            accessTokenExpiresIn = accessTokenExpiration,
            refreshTokenExpiresIn = refreshTokenExpiration
        )
    }

    private fun getAccessSigningKey(): SecretKey {
        return Keys.hmacShaKeyFor(accessSecret.toByteArray())
    }

    private fun getRefreshSigningKey(): SecretKey {
        return Keys.hmacShaKeyFor(refreshSecret.toByteArray())
    }

    private fun generateAccessToken(user: UserLoginDto): String {
        val claims = HashMap<String, Any>()
        claims["roles"] = user.roles
        claims["userId"] = user.id

        return createAccessToken(claims, user.email)
    }

    private fun generateRefreshToken(username: String): String {
        val now = Date()
        val expirationDate = Date(now.time + refreshTokenExpiration)

        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(now)
            .setExpiration(expirationDate)
            .signWith(getRefreshSigningKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    private fun createAccessToken(claims: Map<String, Any>, subject: String): String {
        val now = Date()
        val expirationDate = Date(now.time + accessTokenExpiration)

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(now)
            .setExpiration(expirationDate)
            .signWith(getAccessSigningKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateAccessToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsernameFromAccessToken(token)
        return username == userDetails.username && !isAccessTokenExpired(token)
    }

    fun validateRefreshToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(getRefreshSigningKey())
                .build()
                .parseClaimsJws(token)
            !isRefreshTokenExpired(token)
        } catch (e: Exception) {
            false
        }
    }

    fun extractUsernameFromAccessToken(token: String): String {
        return extractAllClaimsFromAccessToken(token).subject
    }

    fun extractUsernameFromRefreshToken(token: String): String {
        return extractAllClaimsFromRefreshToken(token).subject
    }

    fun extractAllClaimsFromAccessToken(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(getAccessSigningKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    fun extractAllClaimsFromRefreshToken(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(getRefreshSigningKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun isAccessTokenExpired(token: String): Boolean {
        val expiration = extractAllClaimsFromAccessToken(token).expiration
        return expiration.before(Date())
    }

    private fun isRefreshTokenExpired(token: String): Boolean {
        val expiration = extractAllClaimsFromRefreshToken(token).expiration
        return expiration.before(Date())
    }
}