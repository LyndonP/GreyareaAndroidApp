package com.greyarea.grey.greyareaad340;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.test.espresso.Espresso;
import android.support.test.filters.SmallTest;
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
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.View;
import android.widget.EditText;


public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class, true,
            false); // Activity is not launched immediately

    private MainActivity mActivity = null;
    private String mField = " ";
    private Intent intent;
    private SharedPreferences.Editor preferencesEditor;
    private EditText editTextBoxEntry;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(TextBoxMsgSend.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();

        intent = new Intent();
        Context context = getInstrumentation().getTargetContext();

        // create a SharedPreferences editor
        preferencesEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();

        // create an Edit Text box for use by main activity
        editTextBoxEntry = (EditText) mActivity.findViewById(com.greyarea.grey.greyareaad340.R.id.editText1);

    }


    @Test
    public void testViewsCreated() {
        assertNotNull(mActivity);
        assertNotNull(editTextBoxEntry);
    }

    @Test
    public void testViewsVisible() {
        ViewAsserts.assertOnScreen(editTextBoxEntry.getRootView(), editTextBoxEntry);
    }

    @Test
    public void testValidUserInput() {
        editTextBoxEntry.clearComposingText();

        String testUsername = "test";

        // Set SharedPreferences data
        preferencesEditor.putString("username", testUsername);
        preferencesEditor.commit();
    }

    @Test
    public void testViewsDisplayed(){
        View buttonView = mActivity.findViewById(R.id.button1);
        View editTextView = mActivity.findViewById(R.id.editText1);

        assertNotNull(buttonView);
        assertNotNull(editTextView);
    }



    //UI tests for text field & button
    @Test
    public void testLaunchOfSendButtonClick() {

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
        // Check if the add new activity screen is displayed
        Espresso.onView(withId(R.id.textView1)).check(matches(isDisplayed()));

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
        preferencesEditor.putString("username", testUsername);
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