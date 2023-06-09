package com.EZALLC.securityapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.CursorMatchers.withRowString;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasSize;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)

public class InformationDisplayTest {
    @Rule
    public ActivityScenarioRule<IpAddressInfo> InfoActivityTestRule = new ActivityScenarioRule<>(IpAddressInfo.class);
    @Rule
    public ActivityTestRule<Information> intentsTestRule= new ActivityTestRule<>(Information.class);
    @Rule
    public ActivityTestRule<IpAddressInfo> mActivityRule = new ActivityTestRule(IpAddressInfo.class);

    @Before
    public void setUp() throws Exception{
        Intents.init();

    }

   @Test
    public void information_Display() {

        Espresso.onData(allOf(is(instanceOf(Information.class)), is("IP address")));
        Espresso.onData(allOf(is(instanceOf(Information.class)), is("URL")));
        Espresso.onData(allOf(is(instanceOf(Information.class)), is("Cyber security threats")));
    }

    public class ToastMatcher extends TypeSafeMatcher<Root> {


        @Override    public void describeTo(Description description) {
            description.appendText("is toast");
        }

        @Override    public boolean matchesSafely(Root root) {
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
    public void maxMinTextSizeTest(){
        Espresso.onView(withId(R.id.buttonInc)).perform(click());
        Espresso.onView(withId(R.id.buttonInc)).perform(click());
        Espresso.onView(withId(R.id.buttonInc)).perform(click());
        Espresso.onView(withId(R.id.buttonInc)).perform(click());
        Espresso.onView(withId(R.id.buttonInc)).perform(click());
        Espresso.onView(withId(R.id.buttonInc)).perform(click());
        Espresso.onView(withText("Maximum text size reached")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.buttonDec)).perform(click());
        Espresso.onView(withId(R.id.buttonDec)).perform(click());
        Espresso.onView(withId(R.id.buttonDec)).perform(click());
        Espresso.onView(withId(R.id.buttonDec)).perform(click());
        Espresso.onView(withId(R.id.buttonDec)).perform(click());
        Espresso.onView(withId(R.id.buttonDec)).perform(click());
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
