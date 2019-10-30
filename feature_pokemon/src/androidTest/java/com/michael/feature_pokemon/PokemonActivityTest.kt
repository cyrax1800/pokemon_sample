package com.michael.feature_pokemon

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.michael.feature_pokemon.view.PokemonActivity
import com.michael.lib_core.test.FetchingIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class PokemonActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(
        PokemonActivity::class.java,
        false,
        false
    )

    private val itemPosition = 5

    @Before
    fun setUp() {
        activityRule.launchActivity(null)
        IdlingRegistry.getInstance().register(FetchingIdlingResource.get())
    }

    @Test
    fun testPokemonList() {
        // check visibility of recyclerView
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // scroll list into 5
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemPosition))

        // Check if the item 5 has Charizard Text
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .check(ViewAssertions.matches(atPosition(5, ViewMatchers.withText("Charizard"))))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(FetchingIdlingResource.get())
    }
}