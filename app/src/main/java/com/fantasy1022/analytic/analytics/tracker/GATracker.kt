package com.fantasy1022.analytic.analytics.tracker

import android.app.Application
import com.fantasy1022.analytic.R
import com.fantasy1022.analytic.analytics.Event
import com.fantasy1022.analytic.analytics.Event.Companion.TARGET_GA
import com.fantasy1022.analytic.analytics.ScreenEvent
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.HitBuilders
import com.google.android.gms.analytics.Tracker


class GATracker : BaseTracker<Event> {

    private lateinit var tracker: Tracker

    constructor(context: Application, isDebug: Boolean) : super(context, isDebug)

    override fun getTrackerTarget(): Long {
        return TARGET_GA
    }

    override fun setupTracker(context: Application, isDebug: Boolean) {
        val analytics = GoogleAnalytics.getInstance(context)
        tracker = analytics.newTracker(R.xml.global_tracker)
//        tracker = if (isDebug) analytics.newTracker(DEBUG_TOKEN) else analytics.newTracker(RELEASE_TOKEN)
    }

    override fun acceptEvent(event: Event): Boolean {
        return (event is ScreenEvent) || !event.params.isEmpty
    }

    override fun transformEvent(event: Event): Event {
        return event
    }

    override fun postEvent(transformedEvent: Event) {
        if (transformedEvent is ScreenEvent) {
            tracker.setScreenName(transformedEvent.screenName)
            tracker.send(HitBuilders.ScreenViewBuilder().build())
        } else {
            tracker.send(getEventMap(transformedEvent))
        }
    }

    private fun getEventMap(event: Event): Map<String, String> {
        val eventBuilder = HitBuilders.EventBuilder()
                .setCategory(event.params[Event.CATEGORY])
                .setAction(event.params[Event.ACTION])
                .setLabel(event.params[Event.LABEL])

        return eventBuilder.build()
    }

}