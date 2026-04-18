package com.example.zineb_hamdoun_proyectopmdm.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterScreen() {
    val gradientBackground = Brush.verticalGradient(colors = listOf(Color(0xFF2B2B2B), Color(0xFF000000)))
    var sexo by remember { mutableStateOf("M") }

    Box(modifier = Modifier.fillMaxSize().background(gradientBackground)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text("CREAR CUENTA", style = MaterialTheme.typography.headlineMedium, color = Color(0xFFFFD700), fontWeight = FontWeight.ExtraBold)

            Spacer(modifier = Modifier.height(30.dp))


            RegistroInput(label = "Nombre completo", icon = Icons.Default.Person)
            Spacer(modifier = Modifier.height(15.dp))
            RegistroInput(label = "Correo electrónico", icon = Icons.Default.Email)
            Spacer(modifier = Modifier.height(15.dp))
            RegistroInput(label = "Teléfono", icon = Icons.Default.Phone)
            Spacer(modifier = Modifier.height(15.dp))
            RegistroInput(label = "Contraseña", icon = Icons.Default.Lock, isPass = true)

            Spacer(modifier = Modifier.height(25.dp))


            Text("Sexo", color = Color.White, modifier = Modifier.align(Alignment.Start))
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                RadioButton(
                    selected = sexo == "M",
                    onClick = { sexo = "M" },
                    colors = RadioButtonDefaults.colors(selectedColor = Color(0xFFFFD700), unselectedColor = Color.Gray)
                )
                Text("Masculino", color = Color.White)
                Spacer(modifier = Modifier.width(20.dp))
                RadioButton(
                    selected = sexo == "F",
                    onClick = { sexo = "F" },
                    colors = RadioButtonDefaults.colors(selectedColor = Color(0xFFFFD700), unselectedColor = Color.Gray)
                )
                Text("Femenino", color = Color.White)
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("REGISTRARME", color = Color.Black, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


@Composable
fun RegistroInput(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, isPass: Boolean = false) {
    var textState by remember { mutableStateOf("") } // Cada campo necesita su propio estado

    OutlinedTextField(
        value = textState,
        onValueChange = { textState = it },
        label = { Text(label, color = Color.Gray) },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = { Icon(icon, null, tint = Color(0xFFFFD700)) },
        visualTransformation = if (isPass) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
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
}