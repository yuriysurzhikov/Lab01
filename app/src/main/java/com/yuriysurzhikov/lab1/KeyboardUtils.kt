package com.yuriysurzhikov.lab1

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager


object KeyboardUtils {
    @JvmStatic
    fun closeSoftKeyboard(view: View) {
        val imm: InputMethodManager? =
            view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}