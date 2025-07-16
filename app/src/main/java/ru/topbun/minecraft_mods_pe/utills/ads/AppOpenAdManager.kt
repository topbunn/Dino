package ru.topbun.minecraft_mods_pe.utills.ads

import android.app.Activity
import android.app.Application
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.yandex.mobile.ads.common.AdError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.common.MobileAds
import com.yandex.mobile.ads.appopenad.*
import com.yandex.mobile.ads.common.AdRequestConfiguration
import com.yandex.mobile.ads.common.AdRequestError
import ru.topbun.minecraft_mods_pe.BuildConfig

class AppOpenAdManager(private val application: Application) {

    private var appOpenAd: AppOpenAd? = null

    private val adRequest = AdRequestConfiguration.Builder(BuildConfig.OPEN_AD_ID).build()
    private val adLoader = AppOpenAdLoader(application)

    init {
        loadAd()
        ProcessLifecycleOwner.get().lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onStart(owner: LifecycleOwner) {
                showAdIfAvailable()
            }
        })
    }

    private fun loadAd() {
        adLoader.setAdLoadListener(object : AppOpenAdLoadListener {
            override fun onAdLoaded(ad: AppOpenAd) {
                appOpenAd = ad
                appOpenAd?.setAdEventListener(object : AppOpenAdEventListener {
                    override fun onAdShown() {}

                    override fun onAdFailedToShow(adError: AdError) {
                        clearAd()
                        throw RuntimeException(adError.description)
                    }

                    override fun onAdDismissed() {
                        clearAd()
                        loadAd()
                    }

                    override fun onAdClicked() {}

                    override fun onAdImpression(impressionData: ImpressionData?) {}
                })
            }

            override fun onAdFailedToLoad(error: AdRequestError) {
                throw RuntimeException(error.description)
            }
        })
        adLoader.loadAd(adRequest)
    }

    fun showAdIfAvailable(activity: Activity? = null) {
        val currentActivity = activity ?: return
        appOpenAd?.show(currentActivity)
    }

    private fun clearAd() {
        appOpenAd?.setAdEventListener(null)
        appOpenAd = null
    }
}
