package com.EZALLC.securityapp;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.not;

import android.os.IBinder;
import android.view.WindowManager;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.Root;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class emailToastChecker {
    @Rule
    public ActivityTestRule<Search> intentsTestRule = new ActivityTestRule<>(Search.class);


    @Before
    public void setUp() throws Exception {
        Intents.init();

    }


    public class ToastMatcher extends TypeSafeMatcher<Root> {


        @Override
        public void describeTo(Description description) {
            description.appendText("is toast");
        }

        @Override
        public boolean matchesSafely(Root root) {
            int type = root.getWindowLayoutParams().get().type;
            if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
                IBinder windowToken = root.getDecorView().getWindowToken();
                IBinder appToken = root.getDecorView().getApplicationWindowToken();
                if (windowToken == appToken) {
                    //means this window isn't contained by any other windows.
                    return true;
                }
            }
            return false;
        }
    }

    @Test
    public void emailToast() {
        Espresso.onView(withId(R.id.searchInput)).perform(typeText("Skystock19@gmail.com"));
        Espresso.onView(withId(R.id.search_button)).perform(click());
        Espresso.onView(withText("Valid Email Address Skystock19@gmail.com\nSearching")).inRoot(new emailToastChecker.ToastMatcher()).check(matches(isDisplayed()));
        Espresso.onView(withContentDescription("Navigate up")).perform(click());
        Espresso.onView(withId(R.id.searchInput)).perform(typeText("guccifuji73@gmail.com"));
        Espresso.onView(withId(R.id.search_button)).perform(click());
        Espresso.onView(withText("Valid Email Address guccifuji73@gmail.com\nSearching")).inRoot(new emailToastChecker.ToastMatcher()).check(matches(isDisplayed()));
        Espresso.onView(withContentDescription("Navigate up")).perform(click());
        Espresso.onView(withId(R.id.searchInput)).perform(typeText("Temomez123@gmail.com"));
        Espresso.onView(withId(R.id.search_button)).perform(click());
        Espresso.onView(withText("Valid Email Address Temomez123@gmail.com\nSearching")).inRoot(new emailToastChecker.ToastMatcher()).check(matches(isDisplayed()));
    }


    @After
    public void tearDown() throws Exception {
        Intents.release();
    }


}