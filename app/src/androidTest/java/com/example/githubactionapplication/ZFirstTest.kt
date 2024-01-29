package com.example.githubactionapplication

import android.content.Intent
import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.util.HumanReadables
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeoutException


@RunWith(AndroidJUnit4::class)
class ZFirstTest {
    lateinit var scenario: ActivityScenario<HelloActivity>

    @Test
    fun helloWorld() {
        val intent = Intent(InstrumentationRegistry.getInstrumentation().targetContext, HelloActivity::class.java)
        scenario = ActivityScenario.launch(intent)
        onView(withText("Hello Android!")).perform(WaitUntilVisibleAction(100000))  // 100 second
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        scenario.close()
    }

    internal class WaitUntilVisibleAction(private val timeout: Long) : ViewAction {

        override fun getConstraints(): Matcher<View> {
            return Matchers.any(View::class.java)
        }

        override fun getDescription(): String {
            return "waiting for the view milliseconds"
        }

        override fun perform(uiController: UiController, view: View) {
            val endTime = System.currentTimeMillis() + timeout
            do {
                if (view.visibility == View.VISIBLE) {
                    return
                }
                uiController.loopMainThreadForAtLeast(50)
            } while (System.currentTimeMillis() < endTime)
            throw PerformException.Builder()
                .withActionDescription(description)
                .withCause(TimeoutException("Waited $timeout milliseconds"))
                .withViewDescription(HumanReadables.describe(view))
                .build()
        }
    }

}