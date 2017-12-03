package com.fantasy1022.analytic.analytics.tracker

import android.content.Context
import com.fantasy1022.analytic.analytics.Event


abstract class BaseTracker<T>(val context: Context, val isDebug: Boolean) {

    init {
        setupTracker(context, isDebug)
    }


    fun trackEvent(event: Event) {
        if (isOwnEvent(event.target) && acceptEvent(event)) {
            val transformedEvent = transformEvent(event)
            postEvent(transformedEvent)
        }
    }

    protected abstract fun isOwnEvent(@Event.Companion.TrackerTarget target: Long): Boolean

    protected abstract fun setupTracker(context: Context, isDebug: Boolean)

    protected abstract fun acceptEvent(event: Event): Boolean

    protected abstract fun transformEvent(event: Event): T

    protected abstract fun postEvent(transformedEvent: T)
}