package com.greyarea.grey.greyareaad340;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class inputValidation {

    private MainActivity mActivity = new MainActivity();

    @Test
    public void validInput_ReturnsTrue() {
        assertThat(mActivity.inputValidation("cheddar"), is(true));
    }

    @Test
    public void emptyInput_ReturnsFalse() {
        assertThat(mActivity.inputValidation(""), is(false));
    }

    @Test
    public void numericInput_ReturnsTrue() {
        assertThat(mActivity.inputValidation("00000"), is(true));
    }
}
