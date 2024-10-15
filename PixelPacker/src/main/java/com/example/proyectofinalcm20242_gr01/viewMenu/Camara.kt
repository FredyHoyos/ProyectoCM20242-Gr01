package com.example.proyectofinalcm20242_gr01.viewMenu

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectofinalcm20242_gr01.uiNavigation.NavigationUiState
import com.example.proyectofinalcm20242_gr01.uiNavigation.NavigationViewModel

@Composable
fun Camara(){
    val navigationViewModel: NavigationViewModel = viewModel()
    val currentScreen = navigationViewModel.currentScreen.value
    Text("Camara")
    Button(onClick = { navigationViewModel.navigateTo(NavigationUiState.ShowUserData) }) {
        Text("Usario")
    }



    }