package com.yuriysurzhikov.lab1

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private val name1 = "Yuriy"

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        onView(withId(R.id.text_input))
            .check(matches(allOf(isDisplayed(), isEnabled())))
            .perform(typeText(name1), pressImeActionButton())
        val resultText = String.format(
            InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.greetings_template),
            name1
        )
        onView(withId(R.id.greetings_text))
            .check(matches(withText(resultText)))
    }
}