package com.fantasy1022.analytic

import android.os.Bundle
import com.fantasy1022.analytic.analytics.Analytics
import com.fantasy1022.analytic.analytics.Events
import kotlinx.android.synthetic.main.activity_main.*

class SecondActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        playBtn.setOnClickListener({ Analytics.instance.trackEvent(Events.clickPlayBtn()) })
    }
}
