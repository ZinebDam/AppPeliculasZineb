# App de Gestión de Películas - PMDM

Una aplicación Android moderna desarrollada en **Kotlin** y **Jetpack Compose** para la gestión de una biblioteca personal de películas.

## 🚀 Características
* **Autenticación:** Pantalla de Login y Registro con validación de campos.
* **Persistencia de sesión:** Almacenamiento del último usuario registrado mediante `SharedPreferences`.
* **Catálogo de Películas:** Visualización de películas en una `LazyColumn` optimizada con tarjetas personalizadas.
* **Gestión (CRUD):** Capacidad para añadir nuevas películas y editar las existentes mediante un formulario dinámico.
* **Interfaz Moderna:** Uso de componentes Material3, incluyendo diálogos de confirmación, TopBars personalizadas y Floating Action Buttons.

## 🛠️ Tecnologías utilizadas
* **Lenguaje:** Kotlin
* **UI:** Jetpack Compose (Material 3)
* **Navegación:** [Navigation 3 (Runtime)](https://developer.android.com/jetpack/compose/navigation) - Implementación avanzada mediante `NavBackStack` y rutas serializables.
* **SDK:** Compile SDK 36 / Target SDK 35.

## 📁 Estructura del Proyecto
* `MainActivity.kt`: Punto de entrada y configuración central de la navegación.
* `ui/screens/`: Contiene todas las pantallas de la aplicación:
    * `LoginScreen`: Gestión de acceso.
    * `RegisterScreen`: Registro de nuevos usuarios.
    * `MovieListScreen`: Catálogo principal de películas.
    * `MovieFormScreen`: Formulario híbrido para creación y edición.

    

---
Desarrollado por Zineb Hamdoun Matahi para el módulo de PMDM.