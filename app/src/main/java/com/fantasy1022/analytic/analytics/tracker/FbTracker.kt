package com.fantasy1022.analytic.analytics.tracker

import android.app.Application
import android.os.Bundle
import com.facebook.FacebookSdk
import com.facebook.LoggingBehavior
import com.facebook.appevents.AppEventsLogger
import com.fantasy1022.analytic.analytics.Event
import com.fantasy1022.analytic.analytics.Event.Companion.TARGET_FB_ANALYTIC
import com.fantasy1022.analytic.analytics.ScreenEvent


class FbTracker : BaseTracker<Event> {

    private lateinit var fbAnalytics: AppEventsLogger

    constructor(context: Application, isDebug: Boolean) : super(context, isDebug)

    override fun getTrackerTarget(): Long {
        return TARGET_FB_ANALYTIC
    }

    override fun setupTracker(context: Application, isDebug: Boolean) {
        if (isDebug) {
            FacebookSdk.setIsDebugEnabled(true)
            FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS)
        }
        fbAnalytics = AppEventsLogger.newLogger(context)
    }

    override fun acceptEvent(event: Event): Boolean {
        return (event is ScreenEvent) || !event.params.isEmpty
    }

    override fun transformEvent(event: Event): Event {
        return event
    }

    override fun postEvent(transformedEvent: Event) {
        if (transformedEvent is ScreenEvent) {
            fbAnalytics.logEvent(transformedEvent.screenName)
        } else {
            fbAnalytics.logEvent(transformedEvent.params[Event.LABEL], getEventMap(transformedEvent))
        }
    }

    private fun getEventMap(event: Event): Bundle {
        val bundle = Bundle()
        bundle.putString("Category", event.params[Event.CATEGORY])
        bundle.putString("Action", event.params[Event.ACTION])
        bundle.putString("Label", event.params[Event.LABEL])
        return bundle
    }
}