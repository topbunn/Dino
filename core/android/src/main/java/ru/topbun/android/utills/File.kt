package ru.topbun.android.utills

import android.content.Context
import android.os.Environment
import java.io.File
import java.io.FileOutputStream

fun Context.getStringFromFileAssets(path: String) = assets.open(path).bufferedReader().use { it.readText() }

fun getModFile(fileName: String): File? {
    val downloadsDir = File(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
        "mods"
    )
    val targetFile = File(downloadsDir, fileName)
    return if (targetFile.exists() && targetFile.isFile) targetFile else null
}

fun copyAssetToDownloads(context: Context, assetFileName: String): File? {
    return try {
        val inputStream = context.assets.open("files/$assetFileName")
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