package com.EZALLC.securityapp;
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

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.anything;
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
        //Kinda works.
        //Espresso.onView(withId(R.menu.menu_main)).perform(click()).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.textView7)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.searchInput)).perform(typeText("174.216.16.12"));
        Espresso.onView(withId(R.id.searchInput)).check(matches(withText("174.216.16.12")));
        Espresso.onView(withId(R.id.search_button)).perform(click());

        //SystemClock.sleep(1500);

//        Espresso.onView(withId(R.id.Search)).perform(click());
//

        intended(hasComponent(IPHashInfo.class.getName()));
        SystemClock.sleep(15000);
        Espresso.onView(withId(R.id.button)).perform(click());

        //openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        //Espresso.onView(withId(android.R.id.home)).perform(click());

        Espresso.onView(withContentDescription("Navigate up")).perform(click());
        intended(hasComponent(MainActivity.class.getName()));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        Espresso.onView(withText("WatchList")).perform(click());
        intended(hasComponent(WatchList.class.getName()));

        //Mess with one below.
        //Espresso.onData(anything()).inAdapterView(withId(R.id.the_watchlist)).atPosition(0).check(matches(isDisplayed()));

       // Espresso.onView(ViewMatchers.withId(R.id.ip_info_page)).perform(ViewActions.click());

    }
    @After
    public void tearDown() throws Exception{
        Intents.release();
    }

}
