package com.greyarea.grey.greyareaad340;


import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import com.greyarea.grey.greyareaad340.MainActivity;


@RunWith(MockitoJUnitRunner.class)
public class validationTesting {

    private static final String TEST_STRING = "HELLO";

    @Mock
    Context xMockContext;

    @Mock
    SharedPreferences mMockSharedPreferences;

    @Mock
    SharedPreferences.Editor mMockEditor ;

    private SharedPreferencesHelper mMockSharedPreferencesHelper;

    @Before
    public void initMocks() {

        // Create a mocked SharedPreferences.
        mMockSharedPreferencesHelper = createMockSharedPreference();

    }

    @Test
    public void sharedPreferences_SaveAndReadEntry() {

        // Save the personal information to SharedPreferences
        boolean success = mMockSharedPreferencesHelper.saveEntry(TEST_STRING);

        assertThat("SharedPreferenceEntry.save... returns true",
                success, is(true));

        assertEquals(TEST_STRING, mMockSharedPreferencesHelper.getEntry());

    }



    @Test
    public void validateInput() {
        MainActivity mActivity = new MainActivity();
        String validTestPhrase = "This is a test phrase";
        String invalidTestPhase = "";

        assertTrue(mActivity.inputValidation(validTestPhrase));
        assertFalse(mActivity.inputValidation(invalidTestPhase));
    }


    /**
     * Creates a mocked SharedPreferences object for successful read/write
     */
    private SharedPreferencesHelper createMockSharedPreference() {

        // Mocking reading the SharedPreferences as if mMockSharedPreferences was previously written
        // correctly.
        when(mMockSharedPreferences.getString(eq("text_entry"), anyString()))
                .thenReturn(TEST_STRING);

        // Mocking a successful commit.
        when(mMockEditor.commit()).thenReturn(true);

        // Return the MockEditor when requesting it.
        when(mMockSharedPreferences.edit()).thenReturn(mMockEditor);

        return new SharedPreferencesHelper(mMockSharedPreferences);
    }
}