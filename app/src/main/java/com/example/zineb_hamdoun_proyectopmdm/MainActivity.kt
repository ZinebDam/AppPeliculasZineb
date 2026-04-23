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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.zineb_hamdoun_proyectopmdm.ui.screens.LoginScreen
import com.example.zineb_hamdoun_proyectopmdm.ui.screens.MovieFormScreen
import com.example.zineb_hamdoun_proyectopmdm.ui.screens.MovieListScreen
import com.example.zineb_hamdoun_proyectopmdm.ui.screens.RegisterScreen
import com.example.zineb_hamdoun_proyectopmdm.ui.theme.Zineb_hamdoun_proyectopmdmTheme

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
    // Con este hacemos el control de navegación para moverse entre pantallas
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login" // La app empieza siempre en el Login
    ) {
        // Ruta para la pantalla de inicio de sesión
        composable("login") {
            LoginScreen(navController)
        }

        // Ruta para la pantalla de registro
        composable("registro") {
            RegisterScreen(navController)
        }

        // Ruta para el listado de películas
        composable("lista") {
            MovieListScreen(navController)
        }

        // Ruta para añadir una película nueva (formulario vacío)
        composable("formulario") {
            MovieFormScreen(navController = navController)
        }

        // Ruta para editar una película existente
        composable(
            route = "formulario/{titulo}/{director}/{nota}/{genero}",
            arguments = listOf(
                navArgument("titulo") { type = NavType.StringType },
                navArgument("director") { type = NavType.StringType },
                navArgument("nota") { type = NavType.StringType },
                navArgument("genero") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val titulo = backStackEntry.arguments?.getString("titulo")
            val director = backStackEntry.arguments?.getString("director")
            val nota = backStackEntry.arguments?.getString("nota")
            val genero = backStackEntry.arguments?.getString("genero")

            MovieFormScreen(
                navController = navController,
                tituloInicial = titulo,
                directorInicial = director,
                notaInicial = nota,
                generoInicial = genero
            )
        }
    }
}