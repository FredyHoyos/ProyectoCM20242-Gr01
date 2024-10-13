package com.example.proyectofinalcm20242_gr01.uiNavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectofinalcm20242_gr01.viewMenu.Camara
import com.example.proyectofinalcm20242_gr01.viewMenu.DatosUsuario
import com.example.proyectofinalcm20242_gr01.viewMenu.Galeria

@Composable
fun NavigationScreen() {
    val navigationViewModel: NavigationViewModel = viewModel()
    val currentScreen = navigationViewModel.currentScreen.value

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly)
    {

        Button(onClick = { navigationViewModel.navigateTo(NavigationUiState.ShowUserData) }) {
            Text("Usario")
        }
        Button(onClick = { navigationViewModel.navigateTo(NavigationUiState.TakePhoto) }) {
            Text("Cámara")
        }
        Button(onClick = { navigationViewModel.navigateTo(NavigationUiState.OpenGallery) }) {
            Text("Galería")
        }
    }
    Column(modifier = Modifier.padding(100.dp)) {
        when (currentScreen) {

            is NavigationUiState.ShowUserData -> {
                DatosUsuario()
            }
            is NavigationUiState.TakePhoto -> {
                Camara()
            }
            is NavigationUiState.OpenGallery -> {
                Galeria()
            }
        }
    }
}
