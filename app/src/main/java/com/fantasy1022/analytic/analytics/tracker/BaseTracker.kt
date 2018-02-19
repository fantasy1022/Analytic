package com.fantasy1022.analytic.analytics.tracker

import android.app.Application
import com.fantasy1022.analytic.analytics.Event


abstract class BaseTracker<T>(val context: Application, val isDebug: Boolean) {

    init {
        setupTracker(context, isDebug)
    }

    fun trackEvent(event: Event) {
        if (isOwnEvent(event.target) && acceptEvent(event)) {
            val transformedEvent = transformEvent(event)
            postEvent(transformedEvent)
        }
    }

    private fun isOwnEvent(@Event.Companion.TrackerTarget target: Long): Boolean{
        return (target and getTrackerTarget()) === getTrackerTarget()
    }

    protected abstract fun setupTracker(context: Application, isDebug: Boolean)

    protected abstract fun acceptEvent(event: Event): Boolean

    protected abstract fun transformEvent(event: Event): T

    protected abstract fun postEvent(transformedEvent: T)

    protected abstract fun getTrackerTarget():Long
}