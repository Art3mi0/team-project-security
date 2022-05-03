package com.EZALLC.securityapp;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class WatchlistTest {
    @Rule
    public ActivityTestRule<Search> activityActivityTestRule = new ActivityTestRule<Search>(Search.class);

    @Before
    public void setUp() throws Exception{
        Intents.init();
    }


    @Test
    public void goto_Search(){
        //Make sure Watchlist is Empty
        //Goes to search page. Searches for an IP and adds it to the Watchlist. Goes to Watchlist and checks
        //first index. Clicks it. It will appear in the edit text. This tests makes sure it matches to original input.
        Espresso.onView(withId(R.id.searchInput)).perform(typeText("174.216.16.12"));
        Espresso.onView(withId(R.id.searchInput)).check(matches(withText("174.216.16.12")));
        closeSoftKeyboard();
        Espresso.onView(withId(R.id.search_button)).perform(click());

        intended(hasComponent(IPHashInfo.class.getName()));
        SystemClock.sleep(20000);
        Espresso.onView(withId(R.id.button)).perform(click());

        Espresso.onView(withContentDescription("Navigate up")).perform(click());
        intended(hasComponent(Search.class.getName()));

        Espresso.onView(withContentDescription("Navigate up")).perform(click());
        intended(hasComponent(MainActivity.class.getName()));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        Espresso.onView(withText("WatchList")).perform(click());
        intended(hasComponent(WatchList.class.getName()));

        Espresso.onData(Matchers.anything())
                .inAdapterView(withId(R.id.the_watchlist))
                .atPosition(0).perform(click());
        Espresso.onView(withId(R.id.watchlist_input)).check(matches(not(withText(""))));
        Espresso.onView(withId(R.id.watchlist_input)).check(matches(withText("174.216.16.12")));
    }
    @After
    public void tearDown() throws Exception{
        Intents.release();
    }

}
