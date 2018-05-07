package com.greyarea.grey.greyareaad340;


import org.junit.Test;

import static org.junit.Assert.*;

public class validationTesting {

    @Test
    public void validateInput() {
        MainActivity mActivity = new MainActivity();
        String validTestPhrase = "This is a test phrase";
        String invalidTestPhase = "";

        assertTrue(mActivity.inputValidation(validTestPhrase));
        assertFalse(mActivity.inputValidation(invalidTestPhase));
    }
}