package com.example.proyectofinalcm20242_gr01.uiNavigation

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class NavigationViewModel : ViewModel() {
    var currentScreen = mutableStateOf<NavigationUiState>(NavigationUiState.ShowUserData)

    fun navigateTo(screen: NavigationUiState) {
        currentScreen.value = screen
    }
}
