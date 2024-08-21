package com.prodia.test.spaceexplorer.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.prodia.test.spaceexplorer.R
import com.prodia.test.spaceexplorer.presentation.ui.MainActivity
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    val dummyQuery = "Indonesia"

    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun assertGetView(){
        onView(withId(R.id.btn_recent_search)).check(matches(isDisplayed()))
        onView(withId(R.id.et_search_article_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.et_search_article)).check(matches(isDisplayed()))
        onView(withId(R.id.et_search_article)).perform(typeText(dummyQuery), closeSoftKeyboard())
    }

}