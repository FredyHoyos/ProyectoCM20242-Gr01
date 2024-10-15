package com.example.proyectofinalcm20242_gr01.uiNavigation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinalcm20242_gr01.viewMenu.Camara
import com.example.proyectofinalcm20242_gr01.viewMenu.DatosUsuario
import com.example.proyectofinalcm20242_gr01.viewMenu.Galeria



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationScreen() {
    val navigationViewModel: NavigationViewModel = viewModel()
    val currentScreen = navigationViewModel.currentScreen.value
    var expanded by remember { mutableStateOf(true) }

    Column {
        // Menú desplegable
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = true }
        ) {
            DropdownMenuItem(text = { Text("Opción 1") }, onClick = {
                navigationViewModel.navigateTo(NavigationUiState.ShowUserData)
                expanded = true
            })
            DropdownMenuItem(
                text = { Text("Opción 2") },onClick = {
                    navigationViewModel.navigateTo(NavigationUiState.TakePhoto)
                    expanded = false
                })
        }

        DropDownMenu1()

        TopAppBar(
            title = { },
            actions = {
                IconButton(onClick = { navigationViewModel.navigateTo(NavigationUiState.ShowUserData) }) {
                    Icon(Icons.Filled.AccountCircle, contentDescription = "Datos Usuario")
                }
                IconButton(onClick = { navigationViewModel.navigateTo(NavigationUiState.TakePhoto) }) {
                    Icon(Icons.Filled.Face, contentDescription = "Tomar foto")
                }
                IconButton(onClick = { navigationViewModel.navigateTo(NavigationUiState.OpenGallery) }) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Galería")
                }
                IconButton(onClick = { print("Delete") }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Eliminar")
                }
            }
        )


        // Contenido según la pantalla actual
        when (currentScreen) {
            is NavigationUiState.ShowUserData -> {
                DatosUsuario()
            }
            is NavigationUiState.TakePhoto -> {
                Camara()
                Button(
                    onClick = { navigationViewModel.navigateTo(NavigationUiState.ShowUserData) },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Volver")
                }
            }
            is NavigationUiState.OpenGallery -> {
                Galeria()
            }
        }
    }
}


@Composable
fun DropDownMenu1() {
    var expanded by remember { mutableStateOf(false) }
    val contextForToast = LocalContext.current.applicationContext

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "DropdownMenuItem", Modifier.padding(top = 10.dp), fontSize = 20.sp)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp)
                .wrapContentSize(align = Alignment.TopStart),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = {
                    expanded = true
                }
            ) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "Open Menu"
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = {
                        Text("¡Suscríbete!")
                    },
                    onClick = {
                        Toast.makeText(contextForToast, "¡Suscrito😎!", Toast.LENGTH_SHORT).show()
                        expanded = false
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Favorite,
                            contentDescription = null,
                            tint = androidx.compose.ui.graphics.Color.Red
                        )
                    }
                )

                DropdownMenuItem(
                    text = {
                        Text("¡Suscribirse!")
                    },
                    onClick = {
                        Toast.makeText(contextForToast, "Suscribir🙏", Toast.LENGTH_SHORT).show()
                        expanded = false
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Check,
                            contentDescription = null,
                            tint = androidx.compose.ui.graphics.Color.DarkGray
                        )
                    }
                )

                DropdownMenuItem(
                    text = {
                        Text("JetpackCompose.pro")
                    },
                    onClick = {
                        Toast.makeText(contextForToast, "JetpackCompose.pro", Toast.LENGTH_SHORT)
                            .show()
                        expanded = false
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Home,
                            contentDescription = null,
                            tint = androidx.compose.ui.graphics.Color.DarkGray
                        )
                    }
                )
            }
        }
    }
}