package com.assesment.fitpeoassignment.views


import android.support.test.espresso.Espresso.onView
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainActivityTest{

    @get:Rule
    val activityScenarioRule= activityScenarioRule<MainActivity>()

    @Test
    fun testBtnClick(){
    }
}