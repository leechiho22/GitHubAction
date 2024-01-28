package com.example.githubactionapplication

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HelloWorldTest {
    lateinit var scenario: ActivityScenario<HelloActivity>
    private var mIdlingResource: IdlingResource? = null


    @Test
    fun helloWorld() {
        val intent = Intent(InstrumentationRegistry.getInstrumentation().targetContext, HelloActivity::class.java)
        scenario = ActivityScenario.launch(intent)
        Thread.sleep(120000) // 120 second
//        scenario.onActivity { activity ->
//            InstrumentationRegistry.getInstrumentation().getUiAutomation()
//                .grantRuntimePermission(activity.getPackageName(), "android.permission.ACCESS_COARSE_LOCATION")
//            InstrumentationRegistry.getInstrumentation().getUiAutomation()
//                .grantRuntimePermission(activity.getPackageName(), "android.permission.ACCESS_FINE_LOCATION")
//            InstrumentationRegistry.getInstrumentation().getUiAutomation()
//                .grantRuntimePermission(activity.getPackageName(), "android.permission.POST_NOTIFICATIONS")
//        }



        onView(withText("Hello Android!")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //onView(withText("Bad Apple")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        scenario.close()
    }

}