package com.fantasy1022.analytic

import android.app.Application
import com.fantasy1022.analytic.analytics.Analytics
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.crashes.Crashes

class AnalyticApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //TODO:Check isDebug parameter
        Analytics.instance.init(this, false)
    }
}