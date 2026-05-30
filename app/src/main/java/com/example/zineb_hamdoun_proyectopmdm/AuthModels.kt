package com.example.zineb_hamdoun_proyectopmdm

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

// Estructura que recibe el token generado por el servidor
@Serializable
data class AuthResponse(
    val token: String
)