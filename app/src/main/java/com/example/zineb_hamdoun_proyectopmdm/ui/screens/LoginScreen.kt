package com.example.zineb_hamdoun_proyectopmdm.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation3.runtime.NavBackStack
import com.example.zineb_hamdoun_proyectopmdm.ListaRoute
import com.example.zineb_hamdoun_proyectopmdm.R
import com.example.zineb_hamdoun_proyectopmdm.RegistroRoute
import com.example.zineb_hamdoun_proyectopmdm.ui.theme.Zineb_hamdoun_proyectopmdmTheme

@Composable
fun LoginScreen(navController: Any) {

    val context = LocalContext.current
    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Con este efecto recupero el usuario guardado
    LaunchedEffect(Unit) {
        val sharedPref = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val guardado = sharedPref.getString("usuario_guardado", "")
        if (!guardado.isNullOrEmpty()) {
            usuario = guardado
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(R.string.login_titulo_principal),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 4.sp
            )

            Text(
                text = stringResource(R.string.login_subtitulo),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(50.dp))


            Text(
                text = stringResource(R.string.login_etiq_usuario),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.Start).padding(start = 8.dp, bottom = 8.dp),
                fontSize = 14.sp
            )

            OutlinedTextField(
                value = usuario,
                onValueChange = { usuario = it },
                placeholder = { Text(stringResource(R.string.login_pista_usuario), color = MaterialTheme.colorScheme.onSurfaceVariant) },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                )
            )

            Spacer(modifier = Modifier.height(20.dp))


            Text(
                text = stringResource(R.string.login_etiq_pass),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.Start).padding(start = 8.dp, bottom = 8.dp),
                fontSize = 14.sp
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text(stringResource(R.string.login_pista_pass), color = MaterialTheme.colorScheme.onSurfaceVariant) },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                )
            )

            Spacer(modifier = Modifier.height(40.dp))


            Button(
                onClick = {
                    if (usuario.isNotEmpty() && password.isNotEmpty()) {
                        val backStack = navController as androidx.navigation3.runtime.NavBackStack<androidx.navigation3.runtime.NavKey>

                        backStack.add(ListaRoute)
                    } else {
                        Toast.makeText(
                            context,
                            "Por favor, rellena todos los campos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
            ) {
                Text(
                    text = stringResource(R.string.login_boton_entrar),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextButton(onClick = {
                val backStack = navController as androidx.navigation3.runtime.NavBackStack<androidx.navigation3.runtime.NavKey>
                backStack.add(RegistroRoute)
            }) {
                Row {
                    Text(stringResource(R.string.login_sin_cuenta), color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(" " + stringResource(R.string.login_ir_registro),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun LoginScreenPreview() {
    Zineb_hamdoun_proyectopmdmTheme {
        LoginScreen(navController = rememberNavController())
    }
}