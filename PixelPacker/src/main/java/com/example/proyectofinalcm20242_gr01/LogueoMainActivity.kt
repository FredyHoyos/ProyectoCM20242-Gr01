package com.example.proyectofinalcm20242_gr01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalcm20242_gr01.theme.ProyectoFinalCM20242Gr01Theme
import com.example.proyectofinalcm20242_gr01.uiNavigation.NavigationScreen
import androidx.navigation.NavHostController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoFinalCM20242Gr01Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "home") {
                        composable("home") { HomeScreen(navController) }
                        composable("navigationScreen") { NavigationScreen() }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    // Usamos un Column para centrar el botón
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center) // Centrar en la pantalla
    ) {
        Button(
            onClick = { navController.navigate("navigationScreen") },
            modifier = Modifier.padding(16.dp) // Agrega un poco de padding
        ) {
            Text("Ir a NavigationScreen")
        }
    }
}

@Composable
fun NavigationScreen() {
    Text("¡Estás en NavigationScreen!")
}

