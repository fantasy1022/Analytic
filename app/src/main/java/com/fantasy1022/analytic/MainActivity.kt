package com.fantasy1022.analytic

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.crashlytics.android.Crashlytics
import com.fantasy1022.analytic.analytics.Analytics
import com.fantasy1022.analytic.analytics.Events
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = javaClass.simpleName

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                Analytics.instance.trackEvent(Events.openMainPage())
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                Analytics.instance.trackEvent(Events.openDashBoard())
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                Analytics.instance.trackEvent(Events.openNotification())
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        crashBtn.setOnClickListener { Crashlytics.getInstance().crash() }
        playBtn.setOnClickListener({ Analytics.instance.trackEvent(Events.clickPlayBtn()) })
        browseBtn.setOnClickListener({Analytics.instance.trackEvent(Events.clickBrowseBtn())})
    }

    override fun onResume() {
        super.onResume()
    }
}
