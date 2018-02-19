package com.fantasy1022.analytic.analytics.tracker

import android.app.Application
import com.fantasy1022.analytic.R
import com.fantasy1022.analytic.analytics.Event
import com.fantasy1022.analytic.analytics.Event.Companion.TARGET_FLURRY
import com.fantasy1022.analytic.analytics.ScreenEvent
import com.flurry.android.FlurryAgent


class FlurryTracker : BaseTracker<Event> {

    constructor(context: Application, isDebug: Boolean) : super(context, isDebug)

    override fun getTrackerTarget(): Long {
        return TARGET_FLURRY
    }

    override fun setupTracker(context: Application, isDebug: Boolean) {
        FlurryAgent.Builder()
                .withLogEnabled(isDebug)
                .withCaptureUncaughtExceptions(true)
                .build(context, context.getString(R.string.FLURRY_API_KEY))
    }

    override fun acceptEvent(event: Event): Boolean {
        return (event is ScreenEvent) || !event.params.isEmpty
    }

    override fun transformEvent(event: Event): Event {
        return event
    }

    override fun postEvent(transformedEvent: Event) {
        if (transformedEvent is ScreenEvent) {
            FlurryAgent.logEvent(transformedEvent.screenName)
        } else {
            FlurryAgent.logEvent(transformedEvent.params[Event.LABEL])
        }
    }
}