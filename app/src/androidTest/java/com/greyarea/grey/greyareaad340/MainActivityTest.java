package com.greyarea.grey.greyareaad340;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mActivity = null;
    private String mField = " ";

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(TextBoxMsgSend.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
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

    //UI tests for text field & button
    @Test
    public void testUserInputScenario() {

        //input some valid text in the edit text
        Espresso.onView(withId(R.id.editText1)).perform(typeText(mField));
        //close soft keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        Espresso.onView(withId(R.id.button1)).perform(click());
        //checking the text in the text view
        Espresso.onView(withId(R.id.editText1)).check(matches(withText(mField)));

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