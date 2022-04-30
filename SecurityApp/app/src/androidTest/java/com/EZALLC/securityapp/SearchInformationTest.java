package com.EZALLC.securityapp;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import static android.service.autofill.Validators.not;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.content.Context;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.WindowManager;

import androidx.test.espresso.Espresso;

import androidx.test.espresso.Root;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)

public class SearchInformationTest {
    @Rule
    public ActivityScenarioRule<Information> activityActivityTestRule = new ActivityScenarioRule<>(Information.class);
    @Rule
    public IntentsTestRule<Information> intentsTestRule= new IntentsTestRule<>(Information.class);
    @Rule
    public ActivityTestRule<Information_1> mActivityRule = new ActivityTestRule(Information_1.class);

    @Before
    public void setUp() throws Exception{
        Intents.init();
    }

    @Test
    public void information_Display() {
        Espresso.onView(withId(R.id.ListViewInfo))
                .check(matches(withText(containsString("Test one"))));
        Espresso.onView(withId(R.id.ListViewInfo))
                .check(matches(withText(containsString("Test two"))));
        Espresso.onView(withId(R.id.ListViewInfo))
                .check(matches(withText(containsString("Test three"))));
    }
    @Test
    public void onInformationListClick() {
        Espresso.onData(allOf(is(instanceOf(Information.class)), is("Test one"))).perform(click());
        intended(hasComponent(Information_1.class.getName()));
        Espresso.onView(withId(R.id.textView6))
                .check(matches(withText(containsString("https://www.google.com/"))));
        Espresso.onView(withContentDescription("Navigate up")).perform(click());
        intended(hasComponent(Information.class.getName()));
        Espresso.onView(withContentDescription("Navigate up")).perform(click());
        intended(hasComponent(MainActivity.class.getName()));
    }
    public class ToastMatcher extends TypeSafeMatcher<Root> {
        @Override public void describeTo(Description description) {
            description.appendText("is toast");
        }

        @Override public boolean matchesSafely(Root root) {
            int type = root.getWindowLayoutParams().get().type;
            if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
                IBinder windowToken = root.getDecorView().getWindowToken();
                IBinder appToken = root.getDecorView().getApplicationWindowToken();
                if (windowToken == appToken) {
                    //means this window isn't contained by any other windows.
                }
            }
            return false;
        }
    }
    @Test
    public void maxMinTextSizeTest(){
        Espresso.onView(withId(R.id.buttonInc)).perform(click());
        Espresso.onView(withId(R.id.buttonInc)).perform(click());
        Espresso.onView(withId(R.id.buttonInc)).perform(click());
        Espresso.onView(withId(R.id.buttonInc)).perform(click());
        Espresso.onView(withId(R.id.buttonInc)).perform(click());
        Espresso.onView(withText("Maximum text size reached")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.buttonDec)).perform(click());
        Espresso.onView(withId(R.id.buttonDec)).perform(click());
        Espresso.onView(withText("Minimum text size reached")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }


    @After
    public void tearDown() throws Exception{
        Intents.release();
    }

}