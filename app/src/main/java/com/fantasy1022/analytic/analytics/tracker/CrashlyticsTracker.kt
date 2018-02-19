package com.fantasy1022.analytic.analytics.tracker

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.fantasy1022.analytic.analytics.Event
import com.fantasy1022.analytic.analytics.Event.Companion.TARGET_CRASHLYTICS
import com.fantasy1022.analytic.analytics.ScreenEvent
import io.fabric.sdk.android.Fabric


class CrashlyticsTracker : BaseTracker<Event> {

    constructor(context: Application, isDebug: Boolean) : super(context, isDebug)

    override fun getTrackerTarget(): Long {
        return TARGET_CRASHLYTICS
    }

    override fun setupTracker(context: Application, isDebug: Boolean) {
        Fabric.with(context, Crashlytics())
    }

    override fun acceptEvent(event: Event): Boolean {
        return Fabric.isInitialized() && ((event is ScreenEvent) || !event.params.isEmpty)
    }

    override fun transformEvent(event: Event): Event {
        return event
    }

    override fun postEvent(transformedEvent: Event) {
        if (transformedEvent is ScreenEvent) {
            Crashlytics.log(transformedEvent.screenName)
        } else {
            Crashlytics.log(transformedEvent.params[Event.LABEL])
        }
    }

}