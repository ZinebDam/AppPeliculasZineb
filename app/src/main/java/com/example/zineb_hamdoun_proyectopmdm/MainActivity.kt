package com.example.zineb_hamdoun_proyectopmdm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zineb_hamdoun_proyectopmdm.ui.screens.LoginScreen
import com.example.zineb_hamdoun_proyectopmdm.ui.screens.MovieListScreen
import com.example.zineb_hamdoun_proyectopmdm.ui.screens.RegisterScreen
import com.example.zineb_hamdoun_proyectopmdm.ui.theme.Zineb_hamdoun_proyectopmdmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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

    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(navController)
        }


        composable("registro") {
            RegisterScreen(navController)
        }


        composable("lista") {
            MovieListScreen(navController)
        }
    }
}