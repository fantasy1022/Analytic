package com.fantasy1022.analytic.analytics

import android.content.Context
import com.fantasy1022.analytic.analytics.tracker.*


class Analytics private constructor() {

    companion object {
        val instance: Analytics = Analytics()
    }

    private val trackers = ArrayList<BaseTracker<Event>>()

    fun init(context: Context, isDebug: Boolean) {
        trackers.add(GATracker(context, isDebug))
        trackers.add(CrashlyticsTracker(context, isDebug))
        trackers.add(FirebaseGATracker(context, isDebug))
        trackers.add(FlurryTracker(context, isDebug))
    }

    fun trackEvent(event: Event) {
        for (tracker in trackers) {
            tracker.trackEvent(event)
        }
    }

}