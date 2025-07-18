plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.serialization)
    id("kotlin-parcelize")
    id("androidx.room")
}

android {
    namespace = "ru.topbun.minecraft_mods_pe"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.youlovehamit.survival"
        minSdk = 24
        targetSdk = 36
        versionCode = 2
        versionName = "1.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val appKeyMetric = property("metric_key")?.toString() ?: error("Not found appKeyMetric in properties")
        buildConfigField("String", "METRIC_KEY", "\"$appKeyMetric\"")

        val appOpenAdId = property("app_open_ad_id")?.toString() ?: error("Not found appOpenAdId in properties")
        buildConfigField("String", "OPEN_AD_ID", "\"$appOpenAdId\"")

        val interstitialAdId = property("interstitial_ad_id")?.toString() ?: error("Not found interstitialAdId in properties")
        buildConfigField("String", "INSERSTITIAL_AD_ID", "\"$interstitialAdId\"")

        val nativeAdId = property("native_ad_id")?.toString() ?: error("Not found nativeAdId in properties")
        buildConfigField("String", "NATIVE_AD_ID", "\"$nativeAdId\"")

        val applovinSdkKey = property("applovin_sdk_key")?.toString() ?: error("Not found applovin_sdk_key in properties")
        buildConfigField("String", "APPLOVIN_SDK_KEY", "\"$applovinSdkKey\"")

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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }

}

dependencies {
    implementation("com.google.android.play:app-update-ktx:2.1.0")
    implementation("com.google.android.play:review:2.0.1")
    implementation("com.google.android.gms:play-services-ads:23.0.0")
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.kotlinx.serialization.json)

    // Ads
    implementation (libs.mobileads.yandex)
    implementation(libs.applovin.sdk)
    implementation(libs.analytics)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.lifecycle.process)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Voyager
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.tab)
    implementation(libs.voyager.transitions)

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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(project(":domain"))
    
}

configurations.all {
    resolutionStrategy {
        force("androidx.cursoradapter:cursoradapter:1.0.0-rc02")
        force("androidx.resourceinspection:resourceinspection-annotation:1.0.0-rc01")
    }
}