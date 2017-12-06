package io.github.anderscheow.validator.rules.common;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import io.github.anderscheow.validator.constant.Mode;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LengthRuleTest {

    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 10;

    private LengthRule lengthRule;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        lengthRule = null;
    }

    @Test(expected = NullPointerException.class)
    public void validate_EmptySample_ThrowNullPointerException() throws Exception {
        lengthRule = new LengthRule(MIN_LENGTH, MAX_LENGTH);

        lengthRule.validate(null);
    }

    @Test
    public void validate_SampleWithinMinMax_ReturnTrue() throws Exception {
        lengthRule = new LengthRule(MIN_LENGTH, MAX_LENGTH);

        String sample = "7 chars";

        assertTrue(lengthRule.validate(sample));
    }

    @Test
    public void validate_SampleLessThanMin_ReturnFalse() throws Exception {
        lengthRule = new LengthRule(MIN_LENGTH, MAX_LENGTH);

        String sample = "char";

        assertFalse(lengthRule.validate(sample));
    }

    @Test
    public void validate_SampleMoreThanMax_ReturnFalse() throws Exception {
        lengthRule = new LengthRule(MIN_LENGTH, MAX_LENGTH);

        String sample = "more than 10 chars";

        assertFalse(lengthRule.validate(sample));
    }

    @Test(expected = IllegalStateException.class)
    public void validate_SampleSwitchMinMax_ThrowIllegalStateException() throws Exception {
        lengthRule = new LengthRule(MAX_LENGTH, MIN_LENGTH);

        String sample = "more than 10 chars";

        lengthRule.validate(sample);
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = String.format(Locale.getDefault(), "Length must be between %d and %d", MIN_LENGTH, MAX_LENGTH);

        lengthRule = new LengthRule(MIN_LENGTH, MAX_LENGTH);

        assertEquals(errorMessage, lengthRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        lengthRule = new LengthRule(MIN_LENGTH, MAX_LENGTH, errorMessage);

        assertEquals(errorMessage, lengthRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        lengthRule = new LengthRule(MIN_LENGTH, MAX_LENGTH, errorRes);

        assertEquals(errorRes, lengthRule.errorRes());
    }
}