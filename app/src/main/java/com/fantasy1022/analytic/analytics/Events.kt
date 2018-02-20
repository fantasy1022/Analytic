package com.fantasy1022.analytic.analytics

import android.support.v4.util.ArrayMap
import com.fantasy1022.analytic.analytics.Event.Companion.TARGET_ALL
import com.fantasy1022.analytic.analytics.Event.Companion.TARGET_FIREBASE_GA
import com.fantasy1022.analytic.analytics.Event.Companion.TARGET_FLURRY
import com.fantasy1022.analytic.analytics.Event.Companion.TARGET_GA_CRASHLYTICS

class Events {

    companion object {

        fun openMainPage(): ScreenEvent {
            return ScreenEvent(Event.CATEGORY_HOME, TARGET_ALL)
        }

        fun openDashBoard(): ScreenEvent {
            return ScreenEvent(Event.CATEGORY_Dashboard, TARGET_ALL)
        }

        fun openNotification(): ScreenEvent {
            return ScreenEvent(Event.CATEGORY_Notification, TARGET_ALL)
        }

        fun clickPlayBtn(): Event {
            val params: ArrayMap<String, String> = ArrayMap()
            params.put(Event.CATEGORY, Event.CATEGORY_HOME)
            params.put(Event.ACTION, Event.ACTION_PLAY)
            params.put(Event.LABEL, "song")
            return Event(params, TARGET_ALL)
        }

        fun clickBrowseBtn(): Event {
            val params: ArrayMap<String, String> = ArrayMap()
            params.put(Event.CATEGORY, Event.CATEGORY_HOME)
            params.put(Event.ACTION, Event.ACTION_BROWSE)
            params.put(Event.LABEL, "content")
            return Event(params, TARGET_GA_CRASHLYTICS)
        }

    }
}