package com.EZALLC.securityapp;

import static androidx.test.espresso.action.ViewActions.openLinkWithText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

public class TestSearchLink {
    @Rule
    public ActivityTestRule<Search> searchlightTestRule = new ActivityTestRule<>(Search.class);
    @Test
    public void onSearchtextBox() {
        Espresso.onView(withId(R.id.description))
                .perform(openLinkWithText("Here"));
    }
}
