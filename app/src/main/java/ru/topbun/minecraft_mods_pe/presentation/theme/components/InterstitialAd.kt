package ru.topbun.minecraft_mods_pe.presentation.theme.components

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.DisposableEffectResult
import androidx.compose.ui.Modifier
import com.yandex.mobile.ads.common.AdError
import com.yandex.mobile.ads.common.AdRequestConfiguration
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.interstitial.InterstitialAd
import com.yandex.mobile.ads.interstitial.InterstitialAdEventListener
import com.yandex.mobile.ads.interstitial.InterstitialAdLoadListener
import com.yandex.mobile.ads.interstitial.InterstitialAdLoader

@Composable
fun InterstitialAd(activity: Activity, onAdLoaded: ()-> Unit) {
    var interstitialAd: InterstitialAd? = null
    var interstitialAdLoader: InterstitialAdLoader? = null

    fun loadInterstitialAd() {
        val adRequestConfiguration = AdRequestConfiguration.Builder("demo-interstitial-yandex").build()
        interstitialAdLoader?.loadAd(adRequestConfiguration)
    }

    interstitialAdLoader = InterstitialAdLoader(activity.applicationContext).apply {
        setAdLoadListener(object : InterstitialAdLoadListener {
            override fun onAdLoaded(ad: InterstitialAd) {
                interstitialAd = ad
                Log.d("YANDEX_INTER_AD", "Реклама загружена и готова к показу")
                ad.show(activity)
                onAdLoaded()
            }

            override fun onAdFailedToLoad(adRequestError: AdRequestError) {
                Log.d("YANDEX_INTER_AD", "При загрузке рекламы приходила ошибка: ${adRequestError}")
            }
        })
    }

    interstitialAd?.apply {
        setAdEventListener(object : InterstitialAdEventListener {

            override fun onAdShown() {
                Log.d("YANDEX_INTER_AD", "Реклама показана успешно")
                onAdLoaded()
            }
            override fun onAdFailedToShow(adError: AdError) {
                Log.d("YANDEX_INTER_AD", "Во время показа рекламы произошла ошибка: ${adError.description}")

                interstitialAd?.setAdEventListener(null)
                interstitialAd = null

            }
            override fun onAdDismissed() {
                interstitialAd?.setAdEventListener(null)
                interstitialAd = null
                Log.d("YANDEX_INTER_AD", "onAdDismissed()")
            }
            override fun onAdClicked() {
                Log.d("YANDEX_INTER_AD", "onAdClicked()")
            }
            override fun onAdImpression(impressionData: ImpressionData?) {
                Log.d("YANDEX_INTER_AD", "onAdImpression()")
            }
        })
        show(activity)
    }
    loadInterstitialAd()
    DisposableEffect(Unit) {
        onDispose {
            interstitialAdLoader?.setAdLoadListener(null)
            interstitialAdLoader = null
            interstitialAd?.setAdEventListener(null)
            interstitialAd = null
        }
    }
}