package ru.topbun.minecraft_mods_pe.presentation

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            var mod by remember { mutableStateOf<File?>(null) }

            Button(
                onClick = {
                    if (mod != null){
                        installMod(context, mod!!)
                    } else {
                        val file = copyAssetToDownloads(context, "EasyStone.mcaddon")
                        mod = file
                    }
                }
            ) {
                Text(
                    text = if (mod != null) "Установить" else "Скачать"
                )
            }
        }
    }
}

fun copyAssetToDownloads(context: Context, assetFileName: String): File? {
    return try {
        val inputStream = context.assets.open("mods/$assetFileName")
        val downloadsDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "mods")
        if (!downloadsDir.exists()) downloadsDir.mkdirs()

        val outFile = File(downloadsDir, assetFileName)
        inputStream.use { input ->
            FileOutputStream(outFile).use { output ->
                input.copyTo(output)
            }
        }
        outFile
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun installMod(context: Context, file: File) {
    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider", // Укажи в манифесте
        file
    )


    val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(uri, "application/octet-stream")
        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        setPackage("com.mojang.minecraftpe")
    }

    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "Minecraft не установлен", Toast.LENGTH_LONG).show()
    }
}

@Composable
private fun requestPermissionNotify(){
    val contract = ActivityResultContracts.RequestMultiplePermissions()
    val launcher = rememberLauncherForActivityResult(contract = contract) {}
    SideEffect {
        launcher.launch(arrayOf(READ_EXTERNAL_STORAGE) )
    }
}