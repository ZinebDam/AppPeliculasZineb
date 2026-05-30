package com.example.zineb_hamdoun_proyectopmdm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.zineb_hamdoun_proyectopmdm.ui.screens.LoginScreen
import com.example.zineb_hamdoun_proyectopmdm.ui.screens.MovieFormScreen
import com.example.zineb_hamdoun_proyectopmdm.ui.screens.MovieListScreen
import com.example.zineb_hamdoun_proyectopmdm.ui.screens.RegisterScreen
import com.example.zineb_hamdoun_proyectopmdm.ui.theme.Zineb_hamdoun_proyectopmdmTheme
import kotlinx.serialization.Serializable

@Serializable object LoginRoute : NavKey
@Serializable object RegistroRoute : NavKey
@Serializable object ListaRoute : NavKey
@Serializable data class FormularioRoute(
    val titulo: String? = null,
    val director: String? = null,
    val nota: String? = null,
    val genero: String? = null
) : NavKey
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Aqui he Aplicado el tema personalizado del proyecto
            Zineb_hamdoun_proyectopmdmTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background)
                {
                    NavegacionApp()
                }
            }
        }
    }
}

@Composable
fun NavegacionApp() {

    val backStack = rememberNavBackStack(LoginRoute)
    val movieViewModel: MovieViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            // Login
            entry<LoginRoute> {
                LoginScreen(navController = backStack, viewModel = movieViewModel)
            }

            // Registro
            entry<RegistroRoute> {
                RegisterScreen(navController = backStack)
            }

            // Lista
            entry<ListaRoute> {
                MovieListScreen(navController = backStack, viewModel = movieViewModel)
            }

            // Formulario
            entry<FormularioRoute> { key ->
                MovieFormScreen(
                    navController = backStack,
                    tituloInicial = key.titulo,
                    directorInicial = key.director,
                    notaInicial = key.nota,
                    generoInicial = key.genero
                )
            }
        }
    )
}