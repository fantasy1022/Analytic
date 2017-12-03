package com.fantasy1022.analytic

import android.app.Application
import com.fantasy1022.analytic.analytics.Analytics

class AnalyticApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //TODO:Check isDebug parameter
        Analytics.instance.init(this, false)
    }
}