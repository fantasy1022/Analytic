package com.fantasy1022.analytic

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

open abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        FirebaseAnalytics.getInstance(this).setCurrentScreen(this, this.javaClass.simpleName, this.javaClass.simpleName)
    }
}
