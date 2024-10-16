package com.example.proyectofinalcm20242_gr01.viewMenu

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun Camara() {
    val context = LocalContext.current
    var hasCameraPermission by remember { mutableStateOf(false) }
    var isCameraInitialized by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()) { isGranted ->
        hasCameraPermission = isGranted
        if (isGranted) isCameraInitialized = true
    }

    LaunchedEffect(Unit) {
        hasCameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        if (!hasCameraPermission) permissionLauncher.launch(Manifest.permission.CAMERA)
        else isCameraInitialized = true
    }

    if (hasCameraPermission) {
        if (isCameraInitialized) {
            Box {
                CameraPreview()
                Button(onClick = { takePicture(context) }) {
                    Text("Tomar Foto")
                }
            }

        }
    } else {
        Text("Esperando permiso de cámara...")
    }
}

@Composable
fun CameraPreview() {
    val context = LocalContext.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

    AndroidView(
        factory = { ctx ->
            val previewView = PreviewView(ctx)
            val cameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().apply {
                setSurfaceProvider(previewView.surfaceProvider)
            }
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(ctx as LifecycleOwner, CameraSelector.DEFAULT_BACK_CAMERA, preview)
            previewView
        },
        modifier = Modifier.fillMaxSize()
    )
}

private fun takePicture(context: Context) {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

    cameraProviderFuture.addListener(Runnable {
        val cameraProvider = cameraProviderFuture.get()
        val imageCapture = ImageCapture.Builder().build()
        val photoFile = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "${SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())}.jpg")

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(context as LifecycleOwner, CameraSelector.DEFAULT_BACK_CAMERA, imageCapture)

            imageCapture.takePicture(outputOptions, ContextCompat.getMainExecutor(context),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        // Notificar al usuario o actualizar la UI
                    }
                    override fun onError(exception: ImageCaptureException) {
                        // Manejo de errores
                    }
                }
            )
        } catch (exc: Exception) {
            // Manejo de excepciones
        }
    }, ContextCompat.getMainExecutor(context))
}
