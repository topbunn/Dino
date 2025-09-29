package ru.topbun.android.utills

import ru.topbun.android.BuildConfig
import kotlin.random.Random

fun isShowAd() = Random.nextInt(0, 100) in (0..BuildConfig.PERCENT_SHOW_AD)