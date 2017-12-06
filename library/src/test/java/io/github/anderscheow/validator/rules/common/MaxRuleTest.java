package io.github.anderscheow.validator.rules.common;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

public class MaxRuleTest {

    private static final int MAX_LENGTH = 5;

    private MaxRule maxRule;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        maxRule = null;
    }

    @Test(expected = NullPointerException.class)
    public void validate_EmptySample_ThrowNullPointerException() throws Exception {
        maxRule = new MaxRule(MAX_LENGTH);

        maxRule.validate(null);
    }

    @Test
    public void validate_SampleEqualMax_ReturnTrue() throws Exception {
        maxRule = new MaxRule(MAX_LENGTH);

        String sample = "tests";

        assertTrue(maxRule.validate(sample));
    }

    @Test
    public void validate_ValidSampleLessThanMax_ReturnTrue() throws Exception {
        maxRule = new MaxRule(MAX_LENGTH);

        String sample = "test";

        assertTrue(maxRule.validate(sample));
    }

    @Test
    public void validate_SampleMoreThanMax_ReturnTrue() throws Exception {
        maxRule = new MaxRule(MAX_LENGTH);

        String sample = "testing";

        assertFalse(maxRule.validate(sample));
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = String.format(Locale.getDefault(), "Length must not exceed %d characters", MAX_LENGTH);

        maxRule = new MaxRule(MAX_LENGTH);

        assertEquals(errorMessage, maxRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        maxRule = new MaxRule(MAX_LENGTH, errorMessage);

        assertEquals(errorMessage, maxRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        maxRule = new MaxRule(MAX_LENGTH, errorRes);

        assertEquals(errorRes, maxRule.errorRes());
    }
}