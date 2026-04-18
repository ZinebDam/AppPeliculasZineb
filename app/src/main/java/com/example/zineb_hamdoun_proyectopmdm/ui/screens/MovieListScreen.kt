package com.example.zineb_hamdoun_proyectopmdm.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
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
import coil.compose.AsyncImage
import com.example.zineb_hamdoun_proyectopmdm.R
import androidx.compose.foundation.Image


data class Pelicula(
    val titulo: String,
    val director: String,
    val nota: String,
    val genero: String,
    val imagen: Int

)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen() {
    val peliculas = listOf(
        Pelicula("3 metros sobre el cielo", "F. González Molina", "8.5", "Romance",
            R.drawable.tres),
        Pelicula("Fast & Furious", "Rob Cohen", "7.8", "Acción", R.drawable.fast),
        Pelicula("Zipi y Zape", "Oskar Santos", "6.5", "Comedia", R.drawable.zipizape),
        Pelicula("Sofía", "Meryem Benm'Barek", "7.2", "Drama", R.drawable.sofia),
        Pelicula("Culpa mía", "Domingo González", "8.0", "Romance", R.drawable.culpa)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Lista de películas", fontWeight = FontWeight.ExtraBold, color = Color(0xFFFFD700)) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color(0xFF121212))
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}, containerColor = Color(0xFFFFD700)) {
                Icon(Icons.Default.Add, contentDescription = null, tint = Color.Black)
            }
        },
        containerColor = Color(0xFF121212)
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(peliculas) { peli ->
                CardPelicula(peli)
            }
        }
    }
}

@Composable
fun CardPelicula(peli: Pelicula) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = peli.imagen),
                contentDescription = "Cartel de ${peli.titulo}",
                modifier = Modifier
                    .width(90.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)),
                contentScale = ContentScale.Crop
            )


            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = peli.titulo,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1
                )
                Text(
                    text = "Dir: ${peli.director}",
                    color = Color.Gray,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(8.dp))


                Surface(
                    color = Color(0xFF333333),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = peli.genero.uppercase(),
                        color = Color(0xFFFFD700),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            Column(
                modifier = Modifier.padding(end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFD700),
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = peli.nota,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 14.sp
                )
            }
        }
    }
}