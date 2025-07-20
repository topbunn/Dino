plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.serialization) apply false
    alias(libs.plugins.android.library) apply false
    id("androidx.room") version "2.7.2" apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}