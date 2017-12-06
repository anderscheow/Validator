package io.github.anderscheow.validator.rules.common;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

public class NotEqualRuleTest {

    private static final String KEYWORD = "test";

    private NotEqualRule notEqualRule;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        notEqualRule = null;
    }

    @Test(expected = NullPointerException.class)
    public void validate_EmptySample_ThrowNullPointerException() throws Exception {
        notEqualRule = new NotEqualRule(KEYWORD);

        notEqualRule.validate(null);
    }

    @Test
    public void validate_ValidSample_ReturnTrue() throws Exception {
        notEqualRule = new NotEqualRule(KEYWORD);

        String sample = "TEST";

        assertTrue(notEqualRule.validate(sample));
    }

    @Test
    public void validate_InvalidSample_ReturnFalse() throws Exception {
        notEqualRule = new NotEqualRule(KEYWORD);

        String sample = "test";

        assertFalse(notEqualRule.validate(sample));
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = String.format(Locale.getDefault(), "Value equal to '%s'", KEYWORD);

        notEqualRule = new NotEqualRule(KEYWORD);

        assertEquals(errorMessage, notEqualRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        notEqualRule = new NotEqualRule(KEYWORD, errorMessage);

        assertEquals(errorMessage, notEqualRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        notEqualRule = new NotEqualRule(KEYWORD, errorRes);

        assertEquals(errorRes, notEqualRule.errorRes());
    }
}