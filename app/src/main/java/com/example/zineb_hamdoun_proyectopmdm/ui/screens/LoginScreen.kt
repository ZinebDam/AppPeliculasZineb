package com.example.zineb_hamdoun_proyectopmdm.ui.screens

import android.content.Context
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController){

    val context = LocalContext.current
    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val sharedPref = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val guardado = sharedPref.getString("usuario_guardado", "")
        if (!guardado.isNullOrEmpty()) {
            usuario = guardado // Lo pone automáticamente en el cuadro de texto
        }
    }

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFF2B2B2B), Color(0xFF000000))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "CINEVIBE",
                style = MaterialTheme.typography.displayMedium,
                color = Color(0xFFFFD700),
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 4.sp
            )

            Text(
                text = "GESTIÓN MULTIMEDIA",
                color = Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(50.dp))


            Text(
                "Usuario",
                color = Color.White,
                modifier = Modifier.align(Alignment.Start).padding(start = 8.dp, bottom = 8.dp),
                fontSize = 14.sp
            )
            OutlinedTextField(
                value = usuario,
                onValueChange = { usuario = it },
                placeholder = { Text("Tu usuario", color = Color.DarkGray) },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = Color(0xFFFFD700)) },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF1E1E1E),
                    unfocusedContainerColor = Color(0xFF1E1E1E),
                    focusedBorderColor = Color(0xFFFFD700),
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Contraseña",
                color = Color.White,
                modifier = Modifier.align(Alignment.Start).padding(start = 8.dp, bottom = 8.dp),
                fontSize = 14.sp
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("••••••••", color = Color.DarkGray) },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color(0xFFFFD700)) },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF1E1E1E),
                    unfocusedContainerColor = Color(0xFF1E1E1E),
                    focusedBorderColor = Color(0xFFFFD700),
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            
            Button(
                onClick = { navController.navigate("lista") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
            ) {
                Text("INICIAR SESIÓN", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextButton(onClick = { navController.navigate("registro")}) {
                Row {
                    Text("¿Aún no tienes cuenta? ", color = Color.Gray)
                    Text("Regístrate", color = Color(0xFFFFD700), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}