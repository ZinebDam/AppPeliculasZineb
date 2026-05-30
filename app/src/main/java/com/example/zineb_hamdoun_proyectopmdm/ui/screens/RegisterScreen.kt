package com.example.zineb_hamdoun_proyectopmdm.ui.screens


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.zineb_hamdoun_proyectopmdm.R


@Composable
fun RegisterScreen(navController: Any) {

    val context = LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("M") }

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = stringResource(R.string.registro_titulo_pantalla),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(30.dp))

            RegistroInput(
                value = nombre,
                onValueChange = { nombre = it },
                label = stringResource(R.string.registro_etiq_nombre),
                iconRes = android.R.drawable.ic_menu_my_calendar
            )

            Spacer(modifier = Modifier.height(15.dp))

            RegistroInput(
                value = email,
                onValueChange = { email = it },
                label = stringResource(R.string.registro_etiq_email),
                iconRes = android.R.drawable.ic_dialog_email
            )

            Spacer(modifier = Modifier.height(15.dp))

            RegistroInput(
                value = telefono,
                onValueChange = { telefono = it },
                label = stringResource(R.string.registro_etiq_tlf),
                iconRes = android.R.drawable.ic_menu_call
            )

            Spacer(modifier = Modifier.height(15.dp))

            RegistroInput(
                value = password,
                onValueChange = { password = it },
                label = stringResource(R.string.registro_etiq_pass),
                iconRes = android.R.drawable.ic_lock_idle_lock,
                isPass = true
            )

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = stringResource(R.string.registro_etiq_sexo),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.Start)
            )

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                RadioButton(
                    selected = sexo == "M",
                    onClick = { sexo = "M" },
                    colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
                )
                Text(stringResource(R.string.registro_sexo_masc), color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.width(20.dp))
                RadioButton(
                    selected = sexo == "F",
                    onClick = { sexo = "F" },
                    colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
                )
                Text(stringResource(R.string.registro_sexo_fem), color = MaterialTheme.colorScheme.onBackground)
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    if (nombre.isNotEmpty() && email.isNotEmpty() && telefono.isNotEmpty() && password.isNotEmpty()) {
                        val sharedPref = context.getSharedPreferences("prefs", android.content.Context.MODE_PRIVATE)
                        sharedPref.edit().putString("usuario_guardado", email).apply()

                        val backStack = navController as androidx.navigation3.runtime.NavBackStack<androidx.navigation3.runtime.NavKey>
                        backStack.removeLastOrNull()

                        Toast.makeText(context, "Registro completado con éxito", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(stringResource(R.string.registro_boton_enviar), fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun RegistroInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    iconRes: Int,
    isPass: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        visualTransformation = if (isPass) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
        )
    )
}