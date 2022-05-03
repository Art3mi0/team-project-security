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
import android.os.IBinder;
import android.os.SystemClock;
import android.view.WindowManager;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.Root;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
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

import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SignupTest {
    @Rule
    public ActivityTestRule<Signup> activityActivityTestRule = new ActivityTestRule<Signup>(Signup.class);

    @Before
    public void setUp() throws Exception{
        Intents.init();
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
    public void UsedEmail(){
        Espresso.onView(withId(R.id.signup_email_edit)).perform(typeText("nicktest1@gmail.com"));
        Espresso.onView(withId(R.id.signup_password_edit)).perform(typeText("doesntmatter"));
        Espresso.onView(withId(R.id.confirm_password_edit)).perform(typeText("doesntmatter"));

        Espresso.onView(withId(R.id.signup_button)).perform(click());
        Espresso.onView(withText("Authentication failed.")).inRoot(new SignupTest.ToastMatcher()).check(matches(isDisplayed()));

    }
    @Test
    public void NotEmail(){
        Espresso.onView(withId(R.id.signup_password_edit)).perform(typeText("nicktest1@gmail.com"));
        Espresso.onView(withId(R.id.signup_email_edit)).perform(typeText("notanemail"));
        Espresso.onView(withId(R.id.confirm_password_edit)).perform(typeText("nicktest1@gmail.com"));

        Espresso.onView(withId(R.id.signup_button)).perform(click());
        Espresso.onView(withText("Authentication failed.")).inRoot(new SignupTest.ToastMatcher()).check(matches(isDisplayed()));

    }
    @Test
    public void ShortPass(){
        Espresso.onView(withId(R.id.signup_password_edit)).perform(typeText("short"));
        Espresso.onView(withId(R.id.confirm_password_edit)).perform(typeText("short"));
        Espresso.onView(withId(R.id.signup_email_edit)).perform(typeText("nicktest1@gmail.com"));

        Espresso.onView(withId(R.id.signup_button)).perform(click());
        Espresso.onView(withText("Authentication failed.")).inRoot(new SignupTest.ToastMatcher()).check(matches(isDisplayed()));

    }
    @After
    public void tearDown() throws Exception{
        Intents.release();
    }
}
