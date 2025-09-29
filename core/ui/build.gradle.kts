plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "ru.topbun.ui"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val primaryColor = property("primary_color")?.toString() ?: error("Not found primary_color in properties")
        buildConfigField("Integer", "PRIMARY_COLOR", primaryColor)

        val appOpenAdId = property("app_open_ad_id")?.toString() ?: error("Not found appOpenAdId in properties")
        buildConfigField("String", "OPEN_AD_ID", "\"$appOpenAdId\"")

        val interstitialAdId = property("interstitial_ad_id")?.toString() ?: error("Not found interstitialAdId in properties")
        buildConfigField("String", "INSERSTITIAL_AD_ID", "\"$interstitialAdId\"")

        val nativeAdId = property("native_ad_id")?.toString() ?: error("Not found nativeAdId in properties")
        buildConfigField("String", "NATIVE_AD_ID", "\"$nativeAdId\"")

        val applovinAppOpenAdId = property("applovin_open")?.toString() ?: error("Not found applovinAppOpenAdId in properties")
        buildConfigField("String", "APPLOVIN_OPEN_AD_ID", "\"$applovinAppOpenAdId\"")

        val applovinInterstitialAdId = property("applovin_interstitial")?.toString() ?: error("Not found applovinInterstitialAdId in properties")
        buildConfigField("String", "APPLOVIN_INSERSTITIAL_AD_ID", "\"$applovinInterstitialAdId\"")

        val applovinNativeAdId = property("applovin_native")?.toString() ?: error("Not found applovinNativeAdId in properties")
        buildConfigField("String", "APPLOVIN_NATIVE_AD_ID", "\"$applovinNativeAdId\"")

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{
        compose = true
        buildConfig = true
    }
}

dependencies {

    // Ads
    implementation (libs.mobileads.yandex)
    implementation(libs.applovin.sdk)

    // Voyager
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.tab)
    implementation(libs.voyager.transitions)

    implementation(libs.androidx.material.icons.core)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.coil.network.okhttp)
    implementation(libs.coil.compose)
    implementation(libs.androidx.lifecycle.process.v241)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(project(":core:android"))
    implementation(project(":domain"))
    implementation(project(":navigation"))

}
