package com.example.zineb_hamdoun_proyectopmdm.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zineb_hamdoun_proyectopmdm.R
import androidx.compose.foundation.clickable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zineb_hamdoun_proyectopmdm.FormularioRoute
import com.example.zineb_hamdoun_proyectopmdm.Movie
import com.example.zineb_hamdoun_proyectopmdm.MovieViewModel


data class Usuario(
    val id: Int,
    val nombre: String,
    val email: String,
    val telefono: String,
    val sexo: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(navController: Any, viewModel: MovieViewModel) {

    val context = LocalContext.current


    LaunchedEffect(Unit) {
        viewModel.fetchMovies()
    }

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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val backStack =
                        navController as androidx.navigation3.runtime.NavBackStack<androidx.navigation3.runtime.NavKey>
                    backStack.add(FormularioRoute())
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    painter = painterResource(android.R.drawable.ic_input_add),
                    contentDescription = "Añadir"
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->

        if (viewModel.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else if (viewModel.errorMessage != null) {
            // SI HAY UN ERROR, LO PINTAMOS AQUÍ EN MEDIO PARA VER QUÉ PASA
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "⚠️ Error de Conexión con la API",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = viewModel.errorMessage ?: "",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.fetchMovies() }) {
                        Text("Reintentar conectar")
                    }
                }
            }
        } else if (viewModel.moviesList.isEmpty()) {
            // SI NO HAY ERROR PERO LA LISTA VIENE VACÍA DEL SERVIDOR
            Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "El servidor contestó bien, pero la base de datos de películas está vacía (0 películas).",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(24.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentPadding = PaddingValues(all = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items = viewModel.moviesList) { peli ->
                    val backStack = navController as androidx.navigation3.runtime.NavBackStack<*>
                    CardPelicula(peli, backStack)
                }
            }
        }
    }
}

@Composable
fun CardPelicula(peli: Movie, navController: Any) {
    var showDialog by remember { mutableStateOf(false) }
    val viewModel: MovieViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(stringResource(R.string.borrar_titulo_alerta))
            },
            text = {
                Text(stringResource(R.string.borrar_texto_alerta))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.deleteMovie(peli.id)
                        showDialog = false
                    }
                ) {
                    Text(
                        stringResource(R.string.borrar_si),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false }
                ) {
                    Text(
                        stringResource(R.string.borrar_no),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable {
                val backStack =
                    navController as androidx.navigation3.runtime.NavBackStack<androidx.navigation3.runtime.NavKey>
                backStack.add(
                    FormularioRoute(
                        titulo = peli.title,
                        director = peli.directorFullname ?: "Desconocido",
                        nota = peli.rating?.toString() ?: "N/A",
                        genero = "Película"
                    )
                )
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            coil3.compose.AsyncImage(
                model = peli.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .width(90.dp)
                    .fillMaxHeight()
                    .clip(
                        RoundedCornerShape(
                            topStart = 16.dp,
                            bottomStart = 16.dp
                        )
                    ),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .weight(1f)
            ) {
                Text(
                    text = peli.title,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    maxLines = 1
                )

                Text(
                    text = peli.description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = peli.directorFullname ?: "Sin director",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 2.dp
                        ),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            IconButton(
                onClick = { showDialog = true }
            ) {
                Icon(
                    painter = painterResource(android.R.drawable.ic_delete),
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
                    painter = painterResource(android.R.drawable.btn_star_big_on),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(16.dp)
                )

                Text(
                    text = peli.rating?.toString() ?: "N/A",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 13.sp
                )
            }
        }
    }
}

