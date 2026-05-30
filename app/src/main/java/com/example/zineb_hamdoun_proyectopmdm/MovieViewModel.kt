package com.example.zineb_hamdoun_proyectopmdm

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zineb_hamdoun_proyectopmdm.ui.screens.Usuario
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel(){

    var moviesList by mutableStateOf<List<Movie>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var token by mutableStateOf("")
        private set

    fun registerUser(
        email: String,
        password: String,
        firstname: String,
        lastName: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val loginReq = LoginRequest(email = email, password = password)
                val response = RetrofitClient.apiService.login(loginReq)
                token = "Bearer ${response.token}"
                onSuccess()
            } catch (e: Exception) {
                Log.e("LOGIN_ERROR", e.stackTraceToString())
                errorMessage = "Credenciales incorrectas: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }

    fun fetchMovies() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val response = RetrofitClient.apiService.getMovies(token)
                moviesList = response
            } catch (e: Exception) {
                Log.e("MOVIES_ERROR", e.stackTraceToString())
                errorMessage = "Error al leer el catálogo: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }

    fun deleteMovie(movieId: String) {
        viewModelScope.launch {
            try {

                RetrofitClient.apiService.deleteMovie(token, movieId)

                fetchMovies()
            } catch (e: Exception) {
                Log.e("DELETE_ERROR", e.stackTraceToString())
            }
        }
    }
}