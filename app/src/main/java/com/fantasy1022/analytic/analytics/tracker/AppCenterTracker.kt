package com.fantasy1022.analytic.analytics.tracker

import android.app.Application
import com.fantasy1022.analytic.R
import com.fantasy1022.analytic.analytics.Event
import com.fantasy1022.analytic.analytics.Event.Companion.TARGET_APP_CENTER
import com.fantasy1022.analytic.analytics.ScreenEvent
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics


class AppCenterTracker : BaseTracker<Event> {

    constructor(context: Application, isDebug: Boolean) : super(context, isDebug)

    override fun getTrackerTarget(): Long {
        return TARGET_APP_CENTER
    }

    override fun setupTracker(context: Application, isDebug: Boolean) {
        AppCenter.start(context, context.getString(R.string.APP_CENTER_API_KEY),
                Analytics::class.java)
    }

    override fun acceptEvent(event: Event): Boolean {
        return (event is ScreenEvent) || !event.params.isEmpty
    }

    override fun transformEvent(event: Event): Event {
        return event
    }

    override fun postEvent(transformedEvent: Event) {
        if (transformedEvent is ScreenEvent) {
            Analytics.trackEvent(transformedEvent.screenName)
        } else {
            Analytics.trackEvent(transformedEvent.params[Event.LABEL])
        }
    }
}