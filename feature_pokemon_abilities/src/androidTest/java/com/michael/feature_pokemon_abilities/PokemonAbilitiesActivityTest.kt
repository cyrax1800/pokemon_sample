package com.michael.feature_pokemon_abilities

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.airbnb.deeplinkdispatch.DeepLink
import com.michael.feature_pokemon_abilities.view.PokemonAbilitiesActivity
import com.michael.lib_core.AppLink
import com.michael.lib_core.test.FetchingIdlingResource
import org.junit.*
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class PokemonAbilitiesActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(
        PokemonAbilitiesActivity::class.java,
        false,
        false
    )

    @Before
    fun setUp() {
        val intent = Intent()
        intent.putExtra(DeepLink.IS_DEEP_LINK, true)
        intent.putExtra(AppLink.PokemonAbilities.PARAM_ABILITIES, "chlorophyll,overgrow")
        activityRule.launchActivity(intent)
        IdlingRegistry.getInstance().register(FetchingIdlingResource.get())
    }

    @Test
    fun shouldShow2Item() {
        // check visibility of recyclerView
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Check if recyclview has 2 item
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .check { view, noViewFoundException ->
                if (noViewFoundException != null) {
                    throw noViewFoundException
                }
                Assert.assertEquals((view as RecyclerView).adapter?.itemCount, 2)
            }
    }

    @Test
    fun firstItemShouldProperlySet() {
        // check visibility of recyclerView
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Check if the item title is chloropphyll
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .check(
                ViewAssertions.matches(
                    atPositionOnView(0, ViewMatchers.withText("Chlorophyll"), R.id.tvName)
                )
            )
        // Check if the item short effect is expected
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .check(
                ViewAssertions.matches(
                    atPositionOnView(
                        0,
                        ViewMatchers.withText("Doubles Speed during strong sunlight."),
                        R.id.tvShortEffect
                    )
                )
            )
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(FetchingIdlingResource.get())
    }
}