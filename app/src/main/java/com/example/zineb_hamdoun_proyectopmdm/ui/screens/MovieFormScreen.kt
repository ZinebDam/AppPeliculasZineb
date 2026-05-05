package com.example.zineb_hamdoun_proyectopmdm.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.zineb_hamdoun_proyectopmdm.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieFormScreen(
    navController: Any,
    tituloInicial: String? = null,
    directorInicial: String? = null,
    notaInicial: String? = null,
    generoInicial: String? = null
) {

    var titulo by remember { mutableStateOf(tituloInicial ?: "") }
    var director by remember { mutableStateOf(directorInicial ?: "") }
    var nota by remember { mutableStateOf(notaInicial ?: "") }
    var genero by remember { mutableStateOf(generoInicial ?: "") }

    val esEdicion = tituloInicial != null

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        if (esEdicion) stringResource(R.string.form_cabecera_editar)
                        else stringResource(R.string.form_cabecera_crear)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        val backStack = navController as androidx.navigation3.runtime.NavBackStack<androidx.navigation3.runtime.NavKey>
                        backStack.removeLastOrNull()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text(stringResource(R.string.form_etiq_titulo)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                )
            )

            OutlinedTextField(
                value = director,
                onValueChange = { director = it },
                label = { Text(stringResource(R.string.form_etiq_director)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = nota,
                    onValueChange = { nota = it },
                    label = { Text(stringResource(R.string.form_etiq_nota)) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                    )
                )
                OutlinedTextField(
                    value = genero,
                    onValueChange = { genero = it },
                    label = { Text(stringResource(R.string.form_etiq_genero)) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { val backStack = navController as androidx.navigation3.runtime.NavBackStack<*>

                    backStack.removeLastOrNull()},
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    text = if (esEdicion) stringResource(R.string.form_boton_guardar)
                    else stringResource(R.string.form_boton_crear),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}