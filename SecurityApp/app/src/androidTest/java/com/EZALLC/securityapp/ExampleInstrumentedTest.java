package com.EZALLC.securityapp;

import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.content.Context;
import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.EZALLC.securityapp", appContext.getPackageName());
    }
}

class InformationTest {
    @Rule
    public ActivityScenarioRule<Information> activityActivityTestRule = new ActivityScenarioRule<>(Information.class);
    public IntentsTestRule<Information> intentsTestRule= new IntentsTestRule<>(Information.class)

    @Before
    public void setUp() throws Exception{
        Intents.init();
    }

    // Espresso.onView(withId(R.id.textView7)).check(matches(isDisplayed()));
    //Espresso.onView(withId(R.id.searchInput)).perform(typeText("174.216.16.12"));
    //Espresso.onView(withId(R.id.searchInput)).check(matches(withText("174.216.16.12")));
    // Espresso.onView(withId(R.id.search_button)).perform(click());
    //SystemClock.sleep(1500);

    //        Espresso.onView(withId(R.id.Search)).perform(click());
//
    @Test
    public void information_Display() {
        Espresso.onView(withId(R.id.ListViewInfo))
                .check(matches(withText(containsString("Test one"))));
        Espresso.onView(withId(R.id.ListViewInfo))
                .check(matches(withText(containsString("Test two"))));
        Espresso.onView(withId(R.id.ListViewInfo))
                .check(matches(withText(containsString("Test three"))));
    }
    public void onInformationListClick() {
        Espresso.onData(allOf(is(instanceOf(Information.class)), is("Test one"))).perform(click());
        intended(hasComponent(Information_1.class.getName()));
        Espresso.onView(withId(R.id.textView6))
                .check(matches(withText(containsString("https://www.google.com/"))));
        Espresso.onView(withContentDescription("Navigate up")).perform(click());
        intended(hasComponent(Information.class.getName()));
    }
    public void fontSizeTest{

    }

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

    @After
    public void tearDown() throws Exception{
        Intents.release();
    }

}
