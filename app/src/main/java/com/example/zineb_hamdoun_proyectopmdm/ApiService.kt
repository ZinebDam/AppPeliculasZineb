package com.example.zineb_hamdoun_proyectopmdm

import com.example.zineb_hamdoun_proyectopmdm.ui.screens.Usuario
import kotlinx.serialization.Serializable
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("users/login")
    suspend fun login(
        @Body request: LoginRequest
    ): AuthResponse


    @GET("movies")
    suspend fun getMovies(
        @Header("Authorization") token: String
    ): List<Movie>

    @POST("movies")
    suspend fun insertMovie(
        @Header("Authorization") token: String,
        @Body movie: Movie
    ): Movie

    @DELETE("movies/{id}")
    suspend fun deleteMovie(
        @Header("Authorization") token: String,
        @Path("id") movieId: String
    ): retrofit2.Response<Unit>
}