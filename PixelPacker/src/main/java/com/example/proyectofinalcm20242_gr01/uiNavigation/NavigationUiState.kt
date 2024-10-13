package com.example.proyectofinalcm20242_gr01.uiNavigation

sealed class NavigationUiState {
    object ShowUserData : NavigationUiState()
    object TakePhoto : NavigationUiState()
    object OpenGallery : NavigationUiState()
}