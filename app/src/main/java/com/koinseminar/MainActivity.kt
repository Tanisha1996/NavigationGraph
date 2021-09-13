package com.koinseminar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        onView(withId(R.id.my_view))            // withId(R.id.my_view) is a ViewMatcher
//            .perform(click())               // click() is a ViewAction
//            .check(matches(isDisplayed()))  // matches(isDisplayed()) is a ViewAssertion

    }
}