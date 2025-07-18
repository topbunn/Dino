package ru.topbun.minecraft_mods_pe.utills

import android.content.Context
import android.telephony.TelephonyManager

enum class LocationAd{
    RU, OTHER
}

fun Context.getLocation(): LocationAd {
    val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    val simCountry = telephonyManager.simCountryIso
    return if (simCountry == "ru") LocationAd.RU else LocationAd.OTHER
}