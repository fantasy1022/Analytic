package com.fantasy1022.analytic.analytics

import android.support.annotation.IntDef
import android.support.v4.util.ArrayMap


open class Event(val screenName: String = "",
                 @TrackerTarget val target: Long = TARGET_ALL,
                 val params: ArrayMap<String, String> = ArrayMap()) {

    constructor(params: ArrayMap<String, String>, target: Long) : this("", target, params)

    companion object {

        val CATEGORY = "CATEGORY"
        val ACTION = "ACTION"
        val LABEL = "LABEL"

        val CATEGORY_HOME = "Home"
        val CATEGORY_Dashboard = "Dashboard"
        val CATEGORY_Notification = "Notification"

        val ACTION_BROWSE = "Browse"
        val ACTION_PLAY = "Play"

        @IntDef(TARGET_GA, TARGET_CRASHLYTICS, TARGET_FIREBASE_GA, TARGET_FLURRY, TARGET_APP_CENTER,
                TARGET_FB_ANALYTIC, TARGET_GA_CRASHLYTICS, TARGET_ALL)
        @Retention(AnnotationRetention.SOURCE)
        annotation class TrackerTarget

        const val TARGET_GA = 0b00000001L
        const val TARGET_CRASHLYTICS = 0b00000010L
        const val TARGET_FIREBASE_GA = 0b00000100L
        const val TARGET_FLURRY = 0b00001000L
        const val TARGET_APP_CENTER = 0b00010000L
        const val TARGET_FB_ANALYTIC = 0b00100000L
        const val TARGET_GA_CRASHLYTICS = TARGET_GA or TARGET_CRASHLYTICS
        const val TARGET_ALL = TARGET_GA or TARGET_CRASHLYTICS or TARGET_FIREBASE_GA or
                TARGET_FLURRY or TARGET_APP_CENTER or TARGET_FB_ANALYTIC
    }
}
