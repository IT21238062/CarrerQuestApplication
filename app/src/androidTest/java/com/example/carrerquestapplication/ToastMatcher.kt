package com.example.carrerquestapplication

import android.os.IBinder
import android.view.WindowManager
import androidx.test.espresso.Root
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class ToastMatcher : TypeSafeMatcher<Root?>() {

    override fun describeTo(description: Description) {
        description.appendText("is toast")
    }

    override fun matchesSafely(root: Root?): Boolean {
        val type: Int = root?.getWindowLayoutParams()?.get()?.type ?: return false
        return if (type == WindowManager.LayoutParams.TYPE_TOAST) {
            val windowToken: IBinder = root.getDecorView().getWindowToken()
            val appToken: IBinder = root.getDecorView().getApplicationWindowToken()
            windowToken == appToken
        } else false
    }

}
