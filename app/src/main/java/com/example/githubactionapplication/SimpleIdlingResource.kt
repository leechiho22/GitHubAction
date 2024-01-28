package com.example.githubactionapplication

import androidx.annotation.Nullable
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.Volatile
import androidx.test.espresso.IdlingResource;


internal class SimpleIdlingResource : IdlingResource {
    @Nullable
    @Volatile
    private var mCallback: IdlingResource.ResourceCallback? = null

    // Idleness is controlled with this boolean.
    private val mIsIdleNow = AtomicBoolean(true)

    override fun getName(): String {
        return this.javaClass.name
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        mCallback = callback
    }

    override fun isIdleNow(): Boolean {
       return mIsIdleNow.get()
    }

    /**
     * Sets the new idle state, if isIdleNow is true, it pings the [ResourceCallback].
     * @param isIdleNow false if there are pending operations, true if idle.
     */
    fun setIdleState(isIdleNow: Boolean) {
        mIsIdleNow.set(isIdleNow)
        if (isIdleNow && mCallback != null) {
            mCallback!!.onTransitionToIdle()
        }
    }
}