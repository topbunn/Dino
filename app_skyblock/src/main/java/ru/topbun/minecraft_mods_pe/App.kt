package ru.topbun.minecraft_mods_pe

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.applovin.sdk.AppLovinMediationProvider
import com.applovin.sdk.AppLovinPrivacySettings
import com.applovin.sdk.AppLovinSdk
import com.applovin.sdk.AppLovinSdkInitializationConfiguration
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig
import ru.topbun.detail_mod.DetailModScreen
import ru.topbun.favorite.FavoriteScreen
import ru.topbun.feedback.FeedbackScreen
import ru.topbun.instruction.InstructionScreen
import ru.topbun.main.MainScreen
import ru.topbun.navigation.SharedScreen
import ru.topbun.tabs.TabsScreen

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        connectMetrics()
        initAppLovin()
        initSharedScreens()
    }

    private fun connectMetrics(){
        val config = AppMetricaConfig.newConfigBuilder(BuildConfig.METRIC_KEY).build()
        AppMetrica.activate(this, config)
    }

    private fun initAppLovin() {
        val initConfig =
            AppLovinSdkInitializationConfiguration.builder(BuildConfig.APPLOVIN_SDK_KEY)
                .setMediationProvider(AppLovinMediationProvider.MAX)
                .build()

        AppLovinPrivacySettings.setHasUserConsent(true, this)
        AppLovinPrivacySettings.setDoNotSell(false, this)

        AppLovinSdk.getInstance(this).apply {
            settings.setVerboseLogging(true)
            initialize(initConfig) { sdkConfig -> }
        }
    }

    private fun initSharedScreens(){
        ScreenRegistry {
            register<SharedScreen.TabsScreen> {
                TabsScreen
            }
            register<SharedScreen.SplashScreen> {
                ru.topbun.splash.SplashScreen
            }
            register<SharedScreen.MainScreen> {
                MainScreen
            }
            register<SharedScreen.InstructionScreen> {
                InstructionScreen
            }
            register<SharedScreen.FeedbackScreen> {
                FeedbackScreen
            }
            register<SharedScreen.FavoriteScreen> {
                FavoriteScreen
            }
            register<SharedScreen.DetailModScreen> { provider ->
                DetailModScreen(provider.mod)
            }
        }
    }

}