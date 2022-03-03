package com.innocent.languagecommunity.ui

import androidx.lifecycle.Lifecycle
import androidx.paging.PagingSource
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.innocent.languagecommunity.R
import com.innocent.languagecommunity.data.model.CommunityUserInfo
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var mainActivityActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private lateinit var scenerio: ActivityScenario<MainActivity>


    @Before
    fun setup(){
        scenerio = ActivityScenario.launch(MainActivity::class.java)
        scenerio.moveToState(Lifecycle.State.STARTED)
        scenerio.moveToState(Lifecycle.State.RESUMED)
    }


    @Test
    fun referenceCntOfMoreThanZeroHasNewIconDisplayed() {

        val newMember = CommunityUserInfo("Innocent", 1, emptyList(), emptyList(), "URL", 0, "TOPIC")
        val oldMember = CommunityUserInfo("Innocent", 1, emptyList(), emptyList(), "URL", 10, "TOPIC")

        PagingSource.LoadResult.Page(data = listOf(newMember,oldMember),
            prevKey = 1,
            nextKey = 2)

        Espresso.onView(withId(R.id.learning_community_users_recyclerview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(listMatcher().atPosition(0))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(listMatcher().atPosition(1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(listMatcher().atPositionOnView(0, R.id.referenceCntForNew))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(listMatcher().atPositionOnView(0, R.id.referenceCnt))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))

        Espresso.onView(listMatcher().atPositionOnView(1, R.id.referenceCntForNew))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(listMatcher().atPositionOnView(1, R.id.referenceCnt))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    private fun listMatcher(): RecyclerViewMatcher {
        return RecyclerViewMatcher(R.id.learning_community_users_recyclerview)
    }
}