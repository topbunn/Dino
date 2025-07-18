package ru.topbun.minecraft_mods_pe

import android.app.Application
import com.applovin.sdk.AppLovinMediationProvider
import com.applovin.sdk.AppLovinPrivacySettings
import com.applovin.sdk.AppLovinSdk
import com.applovin.sdk.AppLovinSdkInitializationConfiguration
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig

class App: Application() {

    private fun connectMetrics(){
        val config = AppMetricaConfig.newConfigBuilder(BuildConfig.METRIC_KEY).build()
        AppMetrica.activate(this, config)
    }

    override fun onCreate() {
        super.onCreate()
        connectMetrics()
        val initConfig = AppLovinSdkInitializationConfiguration.builder(BuildConfig.APPLOVIN_SDK_KEY)
            .setMediationProvider(AppLovinMediationProvider.MAX)
            .build()

        AppLovinPrivacySettings.setHasUserConsent(true, this)
        AppLovinPrivacySettings.setDoNotSell(false, this)

        AppLovinSdk.getInstance(this).apply {
            settings.setVerboseLogging(true)
            initialize(initConfig) { sdkConfig -> }
        }
    }
}