package io.github.anderscheow.validator.util;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ErrorMessageTest {

    private ErrorMessage errorMessage;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        errorMessage = null;
    }

    @Test
    public void setErrorResNotNegativeOne() throws Exception {
        errorMessage = new ErrorMessage() {};

        @StringRes int errorRes = 123;

        errorMessage.setErrorRes(errorRes);

        assertEquals(errorRes, errorMessage.errorRes());
        assertTrue(errorMessage.isErrorResAvailable());
        assertTrue(errorMessage.isErrorAvailable());
    }

    @Test
    public void setErrorResNegativeOne() throws Exception {
        errorMessage = new ErrorMessage() {};

        @StringRes int errorRes = -1;

        errorMessage.setErrorRes(errorRes);

        assertEquals(errorRes, errorMessage.errorRes());
        assertFalse(errorMessage.isErrorResAvailable());
        assertTrue(errorMessage.isErrorAvailable());
    }

    @Test
    public void setErrorMessageNotEmpty() throws Exception {
        errorMessage = new ErrorMessage() {};

        String errorMsg = "This is a error message";

        errorMessage.setErrorMessage(errorMsg);

        assertEquals(errorMsg, errorMessage.errorMessage());
        assertTrue(errorMessage.isErrorMessageAvailable());
        assertTrue(errorMessage.isErrorAvailable());
    }

    @Test
    public void setErrorMessageEmpty() throws Exception {
        errorMessage = new ErrorMessage() {};

        String errorMsg = "";

        errorMessage.setErrorMessage(errorMsg);

        assertEquals(errorMsg, errorMessage.errorMessage());
        assertFalse(errorMessage.isErrorResAvailable());
        assertFalse(errorMessage.isErrorAvailable());
    }

    @Test
    public void errorRes_DefaultErrorRes() throws Exception {
        errorMessage = new ErrorMessage() {};

        assertEquals(-1, errorMessage.errorRes());
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        errorMessage = new ErrorMessage() {};

        assertEquals("Invalid input", errorMessage.errorMessage());
    }
}