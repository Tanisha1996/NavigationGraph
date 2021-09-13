package com.koinseminar

import android.app.Activity
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {
//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.koinseminar", appContext.packageName)
//    }


    /**
     * A JUnit [@Rule][Rule] to launch your activity under test. This is a replacement
     * for [ActivityInstrumentationTestCase2].
     *
     *
     * Rules are interceptors which are executed for each test method and will run before
     * any of your setup code in the [@Before][Before] method.
     *
     *
     * [ActivityTestRule] will create and launch of the activity for you and also expose
     * the activity under test. To get a reference to the activity you can use
     * the [ActivityTestRule.getActivity] method.
     */
    @Rule
    var mActivityRule: Activity<MainActivity2> = Activity(
        MainActivity2::class.java
    )

    private var mActivity: MainActivity2? = null

    @Before
    fun setActivity() {
        mActivity = mActivityRule.getActivity()
    }

    @Test
    fun autoCompleteTextView_twoSuggestions() {
        // Type "So" to trigger two suggestions.
        onView(withId(R.id.auto_complete_text_view))
            .perform(typeText("So"), closeSoftKeyboard())

        // Check that both suggestions are displayed.
        onView(withText("South China Sea"))
            .inRoot(withDecorView(not(`is`(mActivity!!.getWindow().getDecorView()))))
            .check(matches(isDisplayed()))
        onView(withText("Southern Ocean"))
            .inRoot(withDecorView(not(`is`(mActivity!!.getWindow().getDecorView()))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun autoCompleteTextView_oneSuggestion() {
        // Type "South" to trigger one suggestion.
        onView(withId(R.id.auto_complete_text_view))
            .perform(typeTextIntoFocusedView("South "), closeSoftKeyboard())

        // Should be displayed
        onView(withText("South China Sea"))
            .inRoot(withDecorView(not(`is`(mActivity!!.getWindow().getDecorView()))))
            .check(matches(isDisplayed()))

        // Should not be displayed.
        onView(withText("Southern Ocean"))
            .inRoot(withDecorView(not(`is`(mActivity!!.getWindow().getDecorView()))))
            .check(doesNotExist())
    }

    @Test
    fun autoCompleteTextView_clickAndCheck() {
        // Type text into the text view
        onView(withId(R.id.auto_complete_text_view))
            .perform(typeTextIntoFocusedView("South "), closeSoftKeyboard())

        // Tap on a suggestion.
        onView(withText("South China Sea"))
            .inRoot(withDecorView(not(`is`(mActivity!!.getWindow().getDecorView()))))
            .perform(click())

        // By clicking on the auto complete term, the text should be filled in.
        onView(withId(R.id.auto_complete_text_view))
            .check(matches(withText("South China Sea")))
    }

    @Test
    fun autoCompleteTextView_onDataClickAndCheck() {
        // NB: The autocompletion box is implemented with a ListView, so the preferred way
        // to interact with it is onData(). We can use inRoot here too!
        onView(withId(R.id.auto_complete_text_view))
            .perform(typeText("S"), closeSoftKeyboard())

        // This is useful because some of the completions may not be part of the View Hierarchy
        // unless you scroll around the list.
        onData(Matchers.allOf(Matchers.instanceOf(String::class.java), Matchers.`is`("Baltic Sea")))
            .inRoot(withDecorView(not(`is`(mActivity!!.getWindow().getDecorView()))))
            .perform(click())

        // The text should be filled in.
        onView(withId(R.id.auto_complete_text_view))
            .check(matches(withText("Baltic Sea")))
    }
}