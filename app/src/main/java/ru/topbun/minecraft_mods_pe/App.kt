package ru.topbun.minecraft_mods_pe

import android.app.Application
import com.applovin.sdk.AppLovinMediationProvider
import com.applovin.sdk.AppLovinSdk
import com.applovin.sdk.AppLovinSdkInitializationConfiguration

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        val initConfig = AppLovinSdkInitializationConfiguration.builder(BuildConfig.APPLOVIN_SDK_KEY)
            .setMediationProvider(AppLovinMediationProvider.MAX)
            .build()

        AppLovinSdk.getInstance(this).apply {
            settings.setVerboseLogging(true)
            initialize(initConfig) { sdkConfig -> }
        }
    }
}