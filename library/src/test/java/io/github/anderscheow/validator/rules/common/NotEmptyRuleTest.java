package io.github.anderscheow.validator.rules.common;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

public class NotEmptyRuleTest {

    private NotEmptyRule notEmptyRule;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        notEmptyRule = null;
    }

    @Test(expected = NullPointerException.class)
    public void validate_EmptySample_ThrowNullPointerException() throws Exception {
        notEmptyRule = new NotEmptyRule();

        notEmptyRule.validate(null);
    }

    @Test
    public void validate_ValidSample_ReturnTrue() throws Exception {
        notEmptyRule = new NotEmptyRule();

        String sample = "not empty";

        assertTrue(notEmptyRule.validate(sample));
    }

    @Test
    public void validate_InvalidSample_ReturnFalse() throws Exception {
        notEmptyRule = new NotEmptyRule();

        String sample = "";

        assertFalse(notEmptyRule.validate(sample));
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = "Value must not be empty";

        notEmptyRule = new NotEmptyRule();

        assertEquals(errorMessage, notEmptyRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        notEmptyRule = new NotEmptyRule(errorMessage);

        assertEquals(errorMessage, notEmptyRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        notEmptyRule = new NotEmptyRule(errorRes);

        assertEquals(errorRes, notEmptyRule.errorRes());
    }
}