package ru.topbun.minecraft_mods_pe

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.applovin.sdk.AppLovinMediationProvider
import com.applovin.sdk.AppLovinPrivacySettings
import com.applovin.sdk.AppLovinSdk
import com.applovin.sdk.AppLovinSdkInitializationConfiguration
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig
import kotlinx.serialization.json.Json
import ru.topbun.detail_mod.DetailModScreen
import ru.topbun.domain.entity.ModEntity
import ru.topbun.domain.entity.ModTag
import ru.topbun.domain.entity.ModType
import ru.topbun.favorite.FavoriteScreen
import ru.topbun.feedback.FeedbackScreen
import ru.topbun.instruction.InstructionScreen
import ru.topbun.main.MainScreen
import ru.topbun.navigation.SharedScreen
import ru.topbun.splash.SplashScreen
import ru.topbun.tabs.TabsScreen
import java.io.File
import kotlin.random.Random


fun main() {
    val mods = listOf(

        ModEntity(
            id = 100,
            title = "Carry Animals",
            description = "Sometimes animals are hard to lure using wheat, seeds or carrots they sometimes just ignore the lure or they are too slow. When you leash them sometimes they escape because your too fast. This add-on makes it easier to bring animals anywhere and I hope you enjoy! This simple Add-on lets you carry animals anywhere and you don't need any lure or leash to bring them to a certain spot. You just need to come near them and you will carry them.",
            previewRes = "preview_carry_animals",
            countDownload = Random.nextInt(6000, 12000),
            countFavorite = Random.nextInt(900, 4000),
            tag = ModTag.ANIMAL,
            type = listOf(ModType.ADDON),
            supportVersion = listOf(
                "1.21.50"
            ),
            files = listOf(
                "carry_mod.mcaddon"
            )
        ),

        ModEntity(
            id = 101,
            title = "Mob Crops - Time To Farm Animals",
            description = "Grow mobs like crops — yes, really! \uD83D\uDE04\n" +
                    "Plant Pigs, Cows, Chickens, and Sheep using Mob Crops.\n" +
                    "\n" +
                    "\uD83D\uDD27 How it works:\n" +
                    "\n" +
                    "\uD83E\uDD69 Crafting:\n" +
                    "8 wheat seeds mob meat = Mob Crop\n" +
                    "\n" +
                    "\uD83C\uDF3E Growing:\n" +
                    "Grows in 3 stages — at the last stage, tap to harvest\n" +
                    "\n" +
                    "\uD83C\uDF81 Harvesting:\n" +
                    "Releases the mob & drops normal loot (like killing it)\n" +
                    "\n" +
                    "\uD83C\uDFAF Custom Loot Support:\n" +
                    "Works with other addons’ loot too — fully compatible",
            previewRes = "preview_crops",
            countDownload = Random.nextInt(6000, 12000),
            countFavorite = Random.nextInt(900, 4000),
            tag = ModTag.ANIMAL,
            type = listOf(ModType.ADDON),
            supportVersion = listOf(
                "1.21.90",
                "1.21.70",
                "1.21.80",
            ),
            files = listOf("crops_mod.mcaddon")
        ),

        ModEntity(
            id = 102,
            title = "More Vanilla Animals Bears",
            description = "More Vanilla Animals Bears transforms your Minecraft wildlife experience by introducing an exciting variety of new pandas and bears. Discover unique panda species with distinct patterns, personalities, and behaviors that bring life to your jungles. Roam the wilderness and encounter powerful bear species, each designed to add realism and charm to your game. This mod enhances exploration by populating your world with beautifully detailed animal variants, making biomes feel more alive and engaging. Whether you’re building a zoo, crafting a wildlife sanctuary, or simply enjoying the natural beauty of Minecraft, More Vanilla Animals Bears offers a fresh, immersive way to connect with the environment. Perfect for players who love a blend of creativity and realism in their adventures!",
            previewRes = "preview_more_vanilla",
            countDownload = Random.nextInt(6000, 12000),
            countFavorite = Random.nextInt(900, 4000),
            tag = ModTag.ANIMAL,
            type = listOf(ModType.TEXTURE),
            supportVersion = listOf(
                "1.21.81",
                "1.21.80",
                "1.21.20",
                "1.21",
            ),
            files = listOf(
                "more_vanilla_bp_bears.mcpack",
                "more_vanilla_rp_bears.mcpack"
            )
        ),

        ModEntity(
            id = 103,
            title = "More Vanilla Animals+ Foxes",
            description = "More Vanilla Animals Foxes is a Minecraft mod that expands your world with new fox variants designed in a faithful, vanilla-friendly style. Discover a range of colorful and cute fox types that feel right at home in the Minecraft universe—no realism, just charm and creativity! Whether you're exploring snowy forests or building a magical woodland, these new foxes bring variety and personality without disrupting the core look and feel of the game. Ideal for vanilla players who want to enhance their world with more animal diversity while keeping things light, fun, and Minecraft-authentic.",
            previewRes = "preview_more_vanilla_fox",
            countDownload = Random.nextInt(6000, 12000),
            countFavorite = Random.nextInt(900, 4000),
            tag = ModTag.ANIMAL,
            type = listOf(ModType.TEXTURE),
            supportVersion = listOf(
                "1.20",
                "1.21",
            ),
            files = listOf(
                "more_vanilla_bp_fox.mcpack",
                "more_vanilla_rp_fox.mcpack"
            )
        ),

        ModEntity(
            id = 104,
            title = "World Animals Addon",
            description = "All animals can be found in our Minecraft worlds according to their respective biome, some are tamable, others are aggressive and not capable of taming.",
            previewRes = "preview_world_animal",
            countDownload = Random.nextInt(6000, 12000),
            countFavorite = Random.nextInt(900, 4000),
            tag = ModTag.ANIMAL,
            type = listOf(ModType.ADDON),
            supportVersion = listOf(
                "1.19",
                "1.20",
                "1.21",
            ),
            files = listOf(
                "world_animal.mcaddon"
            )
        ),

        ModEntity(
            id = 105,
            title = "yCreatures Savanna - Safari Days Update",
            description = "yCreatures, an amazing super complete add-on that brings even more fun to your Minecraft worlds!!\n" +
                    "\n" +
                    "This addon contains several new animals for your game, some cute, some not so friendly, but all amazing and super fun, with various varieties of animation, behavior, models and textures.",
            previewRes = "preview_savanna",
            countDownload = Random.nextInt(6000, 12000),
            countFavorite = Random.nextInt(900, 4000),
            tag = ModTag.ANIMAL,
            type = listOf(ModType.ADDON),
            supportVersion = listOf(
                "1.19",
                "1.20",
                "1.21",
            ),
            files = listOf(
                "worldy-library-1_0_1.mcaddon",
                "ycreatures-savanna-v1_0_5.mcaddon",
            )
        ),

    )
    val file = File("animal_mods.json")
    file.writeText(Json.encodeToString(mods))
}

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
            register<SharedScreen.TabsScreen>{
                TabsScreen
            }
            register<SharedScreen.SplashScreen>{
                SplashScreen
            }
            register<SharedScreen.MainScreen>{
                MainScreen
            }
            register<SharedScreen.InstructionScreen>{
                InstructionScreen
            }
            register<SharedScreen.FeedbackScreen>{
                FeedbackScreen
            }
            register<SharedScreen.FavoriteScreen>{
                FavoriteScreen
            }
            register<SharedScreen.DetailModScreen>{ provider ->
                DetailModScreen(provider.mod)
            }
        }
    }

}