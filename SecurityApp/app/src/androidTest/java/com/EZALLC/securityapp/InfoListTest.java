package com.EZALLC.securityapp;

import static androidx.test.espresso.action.ViewActions.click;
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
public class InfoListTest {
    @Rule
    public ActivityTestRule<Information> intentsTestRule = new ActivityTestRule<>(Information.class);
    @Rule
    public ActivityTestRule<IpAddressInfo> mActivityRule = new ActivityTestRule(IpAddressInfo.class);

    @Before
    public void setUp() throws Exception{
        Intents.init();

    }
    @Test
    public void onInformationListClick() {
        Espresso.onData(allOf(is(instanceOf(Information.class)), is("IP Address")));
        Espresso.onData(anything())
                .inAdapterView(withId(R.id.ListViewInfo))
                .atPosition(0)
                .perform(click());
        intended(hasComponent(IpAddressInfo.class.getName()));
        Espresso.onView(withId(R.id.iPInfoBox)).check(matches(withText(containsString("IP address"))));
        Espresso.onView(withContentDescription("Navigate up")).perform(click());
        intended(hasComponent(Information.class.getName()));
        Espresso.onView(withContentDescription("Navigate up")).perform(click());
        intended(hasComponent(MainActivity.class.getName()));
    }
    @After
    public void tearDown() throws Exception{
        Intents.release();
    }
}
