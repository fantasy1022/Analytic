package com.fantasy1022.analytic.analytics.tracker

import android.app.Application
import android.os.Bundle
import com.fantasy1022.analytic.analytics.Event
import com.fantasy1022.analytic.analytics.Event.Companion.TARGET_FIREBASE_GA
import com.fantasy1022.analytic.analytics.ScreenEvent
import com.google.firebase.analytics.FirebaseAnalytics


class FirebaseGATracker : BaseTracker<Event> {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    constructor(context: Application, isDebug: Boolean) : super(context, isDebug)

    override fun getTrackerTarget(): Long {
        return TARGET_FIREBASE_GA;
    }

    override fun setupTracker(context: Application, isDebug: Boolean) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(context)
    }

    override fun acceptEvent(event: Event): Boolean {
        return (event is ScreenEvent) || !event.params.isEmpty
    }

    override fun transformEvent(event: Event): Event {
        return event
    }

    override fun postEvent(transformedEvent: Event) {
        if (transformedEvent is ScreenEvent) {
            firebaseAnalytics.logEvent(transformedEvent.screenName, null)
        } else {
            firebaseAnalytics.logEvent(transformedEvent.params[Event.ACTION]!!, getEventMap(transformedEvent))
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