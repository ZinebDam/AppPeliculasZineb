package com.example.zineb_hamdoun_proyectopmdm

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: String,
    val title: String,
    val rating: Double? = null,
    val description: String = "",
    val imageUrl: String = "",
    val directorFullname: String? = null
)