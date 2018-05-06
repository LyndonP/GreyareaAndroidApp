package com.greyarea.grey.greyareaad340;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

import android.content.SharedPreferences;


public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class, true,
            false); // Activity is not launched immediately

    private MainActivity mActivity = null;
    private String mField = " ";
    private Intent intent;
    private String xInput = "T";
    private SharedPreferences.Editor preferencesEditor;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(TextBoxMsgSend.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();

        intent = new Intent();
        Context context = getInstrumentation().getTargetContext();

        // create a SharedPreferences editor
        preferencesEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();


    }

    //UI tests for text field & button
    @Test
    public void testLaunchOfSendButtonClick() {
        assertNotNull(mActivity.findViewById(R.id.button1));

        // perform onclick action on a view
        onView(withId(R.id.button1)).perform(click());

        Activity secondActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

        assertNotNull(secondActivity);

    }

    @Test
    public void testUserInputScenario() {

        //input some valid text in the edit text
        Espresso.onView(withId(R.id.editText1)).perform(typeText(mField));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        Espresso.onView(withId(R.id.button1)).perform(click());

    }


    // Unit tests for validation logic
    @Test
    public void testEditTextBoxForValidEntries() {

        assertFalse(mField.isEmpty());
        // clears the Edit Text entry box
        Espresso.onView(withId(R.id.editText1)).perform(clearText());
        // test to see if blank entry is permitted (expected toast)
        Espresso.onView(withId(R.id.button1)).perform(click());

    }


    // Tests for data storage & retrieval
    @Test
    public void populateUsernameFromSharedPrefsTest() {
        String testUsername = "test";

        // Set SharedPreferences data
        preferencesEditor.putString("test1", testUsername);
        preferencesEditor.commit();

        // Launch activity
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.editText1))
                .check(matches(isDisplayed()));

    }


    @Test
    public void onCreate() {
    }


    @Test
    public void onCreateOptionsMenu() {
    }


    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}