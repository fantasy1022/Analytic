package com.fantasy1022.analytic.analytics

import android.content.Context
import com.fantasy1022.analytic.analytics.tracker.BaseTracker
import com.fantasy1022.analytic.analytics.tracker.CrashlyticsTracker
import com.fantasy1022.analytic.analytics.tracker.GATracker


class Analytics private constructor() {

    companion object {
        val instance: Analytics = Analytics()
    }

    private val trackers = ArrayList<BaseTracker<Event>>()

    fun init(context: Context, isDebug: Boolean) {
        trackers.add(GATracker(context, isDebug))
        trackers.add(CrashlyticsTracker(context, isDebug))
    }

    fun trackEvent(event: Event) {
        for (tracker in trackers) {
            tracker.trackEvent(event)
        }
    }

}