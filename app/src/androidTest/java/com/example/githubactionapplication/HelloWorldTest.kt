package com.example.githubactionapplication

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class HelloWorldTest {
    lateinit var scenario: ActivityScenario<HelloActivity>

    @Test
    fun helloWorld() {
        val intent = Intent(InstrumentationRegistry.getInstrumentation().targetContext, HelloActivity::class.java)
        scenario = ActivityScenario.launch(intent)
        Thread.sleep(1000)
        onView(withText("Hello Android!")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        scenario.close()
    }
}