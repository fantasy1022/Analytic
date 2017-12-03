package com.fantasy1022.analytic.analytics

import com.fantasy1022.analytic.analytics.Event.Companion.TARGET_ALL

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

    }
}