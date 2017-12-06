package io.github.anderscheow.validator.rules.common;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

public class MinRuleTest {

    private static final int MIN_LENGTH = 5;

    private MinRule minRule;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        minRule = null;
    }

    @Test(expected = NullPointerException.class)
    public void validate_EmptySample_ThrowNullPointerException() throws Exception {
        minRule = new MinRule(MIN_LENGTH);

        minRule.validate(null);
    }

    @Test
    public void validate_SampleEqualMin_ReturnTrue() throws Exception {
        minRule = new MinRule(MIN_LENGTH);

        String sample = "tests";

        assertTrue(minRule.validate(sample));
    }

    @Test
    public void validate_SampleMoreThanMin_ReturnTrue() throws Exception {
        minRule = new MinRule(MIN_LENGTH);

        String sample = "testing";

        assertTrue(minRule.validate(sample));
    }

    @Test
    public void validate_SampleLessThanMin_ReturnTrue() throws Exception {
        minRule = new MinRule(MIN_LENGTH);

        String sample = "test";

        assertFalse(minRule.validate(sample));
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = String.format(Locale.getDefault(), "Length must exceed at least %d characters", MIN_LENGTH);

        minRule = new MinRule(MIN_LENGTH);

        assertEquals(errorMessage, minRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        minRule = new MinRule(MIN_LENGTH, errorMessage);

        assertEquals(errorMessage, minRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        minRule = new MinRule(MIN_LENGTH, errorRes);

        assertEquals(errorRes, minRule.errorRes());
    }
}