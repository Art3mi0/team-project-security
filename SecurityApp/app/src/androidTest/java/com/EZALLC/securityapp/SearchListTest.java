package com.EZALLC.securityapp;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.openLinkWithText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.not;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(AndroidJUnit4.class)
public class SearchListTest {

    @Rule
    public ActivityTestRule<Search> searchlightTestRule = new ActivityTestRule<>(Search.class);

    @Before
    public void setUp() throws Exception{
        Intents.init();

    }
   @Test
    public void onRecentSearchListClickIP() {
        Espresso.onView(withId(R.id.searchInput)).perform(typeText("123.123.123.123"));
        Espresso.onView(withId(R.id.search_button)).perform(click());
        intended(hasComponent(IPHashInfo.class.getName()));
        SystemClock.sleep(10000);
        Espresso.onView(withContentDescription("Navigate up")).perform(click());
        closeSoftKeyboard();
        SystemClock.sleep(5000);
        Espresso.onData(anything())
              .inAdapterView(withId(R.id.SearchList))
               .atPosition(0).perform(click());
        Espresso.onView(withId(R.id.searchInput)).check(matches(not(withText(""))));
        Espresso.onView(withId(R.id.search_button)).perform(click());
        Espresso.onView(withContentDescription("Navigate up")).perform(click());
    }
    @Test
    public void onRecentSearchListClickEmail() {
        Espresso.onView(withId(R.id.searchInput)).perform(typeText("skystock19@gmail.com"));
        Espresso.onView(withId(R.id.search_button)).perform(click());
        intended(hasComponent(IPHashInfo.class.getName()));
        SystemClock.sleep(10000);
        Espresso.onView(withContentDescription("Navigate up")).perform(click());
        closeSoftKeyboard();
        SystemClock.sleep(5000);
        Espresso.onData(anything())
                .inAdapterView(withId(R.id.SearchList))
                .atPosition(0).perform(click());
        Espresso.onView(withId(R.id.searchInput)).check(matches(not(withText(""))));
        Espresso.onView(withId(R.id.search_button)).perform(click());
        Espresso.onView(withContentDescription("Navigate up")).perform(click());
    }
    @Test
    public void onRecentSearchListClickURL() {
        Espresso.onView(withId(R.id.searchInput)).perform(typeText("https://www.google.com/"));
        Espresso.onView(withId(R.id.search_button)).perform(click());
        intended(hasComponent(IPHashInfo.class.getName()));
        SystemClock.sleep(10000);
        Espresso.onView(withContentDescription("Navigate up")).perform(click());
        closeSoftKeyboard();
        SystemClock.sleep(5000);
        Espresso.onData(anything())
                .inAdapterView(withId(R.id.SearchList))
                .atPosition(0).perform(click());
        Espresso.onView(withId(R.id.searchInput)).check(matches(not(withText(""))));
        Espresso.onView(withId(R.id.search_button)).perform(click());
        Espresso.onView(withContentDescription("Navigate up")).perform(click());
    }
   @After
   public void tearDown() throws Exception{
        Intents.release();
    }
}