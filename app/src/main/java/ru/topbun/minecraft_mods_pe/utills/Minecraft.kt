package ru.topbun.minecraft_mods_pe.utills

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File

fun installMod(context: Context, file: File) {
    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
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
        Toast.makeText(context, context.getString(ru.topbun.domain.R.string.minecraft_is_not_installed), Toast.LENGTH_LONG).show()
    }
}