package com.michael.feature_pokemon_detail

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.airbnb.deeplinkdispatch.DeepLink
import com.michael.feature_pokemon_detail.view.EvolutionItem
import com.michael.feature_pokemon_detail.view.PokemonDetailActivity
import com.michael.lib_core.AppLink
import com.michael.lib_core.test.FetchingIdlingResource
import com.mikepenz.fastadapter.FastAdapter
import org.junit.*
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class PokemonDetailActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(
        PokemonDetailActivity::class.java,
        false,
        false
    )

    @Before
    fun setUp() {
        val intent = Intent()
        intent.putExtra(DeepLink.IS_DEEP_LINK, true)
        intent.putExtra(AppLink.PokemonDetail.PARAM_NAME, "bulbasaur")
        activityRule.launchActivity(intent)
        IdlingRegistry.getInstance().register(FetchingIdlingResource.get())
    }

    @Test
    fun shouldShowTitle() {
        Espresso.onView(ViewMatchers.withId(R.id.tvTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.tvTitle))
            .check(ViewAssertions.matches(ViewMatchers.withText("Bulbasaur")))
    }

    @Test
    fun shouldHas2Type() {
        Espresso.onView(ViewMatchers.withId(R.id.rvType))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Check if recyclview has 2 item
        Espresso.onView(ViewMatchers.withId(R.id.rvType))
            .check { view, noViewFoundException ->
                if (noViewFoundException != null) {
                    throw noViewFoundException
                }
                Assert.assertEquals((view as RecyclerView).adapter?.itemCount, 2)
            }
    }

    @Test
    fun shouldHave3EvolutionChain() {
        Espresso.onView(ViewMatchers.withId(R.id.scrollView))
            .perform(ViewActions.swipeUp())

        Espresso.onView(ViewMatchers.withId(R.id.rvEvolutionChain))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Check if recyclview has 2 item
        Espresso.onView(ViewMatchers.withId(R.id.rvEvolutionChain))
            .check { view, noViewFoundException ->
                if (noViewFoundException != null) {
                    throw noViewFoundException
                }
                Assert.assertEquals((view as RecyclerView).adapter?.itemCount, 3)
            }
    }

    @Test
    fun bulbasaurEvolutionChainShouldSelected() {
        Espresso.onView(ViewMatchers.withId(R.id.scrollView))
            .perform(ViewActions.swipeUp())

        Espresso.onView(ViewMatchers.withId(R.id.rvEvolutionChain))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Check if recyclview has 2 item
        Espresso.onView(ViewMatchers.withId(R.id.rvEvolutionChain))
            .check { view, noViewFoundException ->
                if (noViewFoundException != null) {
                    throw noViewFoundException
                }
                val item = ((view as RecyclerView).adapter as FastAdapter<EvolutionItem>).getItem(0)
                Assert.assertEquals(item?.isSelected, true)
            }
    }

    @Test
    fun shouldHaveIvysaurEvolutionChain() {
        Espresso.onView(ViewMatchers.withId(R.id.scrollView))
            .perform(ViewActions.swipeUp())

        Espresso.onView(ViewMatchers.withId(R.id.rvEvolutionChain))

        // Check if the item short effect is expected
        Espresso.onView(ViewMatchers.withId(R.id.rvEvolutionChain))
            .check(
                ViewAssertions.matches(
                    atPositionOnView(
                        1,
                        ViewMatchers.withText("Ivysaur"),
                        R.id.tvName
                    )
                )
            )
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(FetchingIdlingResource.get())
    }
}