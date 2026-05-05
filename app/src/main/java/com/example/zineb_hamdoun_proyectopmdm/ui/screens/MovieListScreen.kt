package com.example.zineb_hamdoun_proyectopmdm.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zineb_hamdoun_proyectopmdm.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.zineb_hamdoun_proyectopmdm.FormularioRoute


// Aqui he definido la estructura de datos para las películas
data class Pelicula(
    val id: Int,
    val titulo: String,
    val director: String,
    val nota: String,
    val genero: String,
    val imagen: Int
)

data class Usuario(
    val id: Int,
    val nombre: String,
    val email: String,
    val telefono: String,
    val sexo: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(navController: Any) {

    val context = LocalContext.current


    // Lista de películas con IDs únicos
    val peliculas = listOf(
        Pelicula(1, "3 metros sobre el cielo", "F. González Molina", "8.5", "Romance", R.drawable.tres),
        Pelicula(2, "Fast & Furious", "Rob Cohen", "7.8", "Acción", R.drawable.fast),
        Pelicula(3, "Zipi y Zape", "Oskar Santos", "6.5", "Comedia", R.drawable.zipizape),
        Pelicula(4, "Sofía", "Meryem Benm'Barek", "7.2", "Drama", R.drawable.sofia),
        Pelicula(5, "Culpa mía", "Domingo González", "8.0", "Romance", R.drawable.culpa)
    )

    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.lista_titulo_barra),
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        floatingActionButton = {
            // Botón para ir al formulario de añadir película
            FloatingActionButton(
                onClick = {

                    val backStack = navController as androidx.navigation3.runtime.NavBackStack<androidx.navigation3.runtime.NavKey>

                    backStack.add(FormularioRoute())
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Añadir")
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = peliculas) { peli ->

                val backStack = navController as androidx.navigation3.runtime.NavBackStack<*>

                CardPelicula(
                    peli = peli,
                    navController = backStack
                )
            }
        }
    }
}

@Composable
fun CardPelicula(peli: Pelicula, navController: Any) {
    var showDialog by remember { mutableStateOf(false) }

    // Este es el código para el diálogo de confirmación para borrar
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(stringResource(R.string.borrar_titulo_alerta)) },
            text = { Text(stringResource(R.string.borrar_texto_alerta)) },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(stringResource(R.string.borrar_si), color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(stringResource(R.string.borrar_no), color = MaterialTheme.colorScheme.primary)
                }
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable {

                val backStack = navController as androidx.navigation3.runtime.NavBackStack<androidx.navigation3.runtime.NavKey>


                backStack.add(
                    FormularioRoute(
                        titulo = peli.titulo,
                        director = peli.director,
                        nota = peli.nota,
                        genero = peli.genero
                    )
                )
            },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = peli.imagen),
                contentDescription = null,
                modifier = Modifier
                    .width(90.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .weight(1f)
            ) {
                Text(
                    text = peli.titulo,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    maxLines = 1
                )
                Text(
                    text = "Dir: ${peli.director}",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = peli.genero.uppercase(),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Botón(papelera) que lleva al diálogo de borrar
            IconButton(onClick = { showDialog = true }) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Borrar",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(20.dp)
                )
            }

            Column(
                modifier = Modifier.padding(end = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = peli.nota,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 13.sp
                )
            }
        }
    }
}