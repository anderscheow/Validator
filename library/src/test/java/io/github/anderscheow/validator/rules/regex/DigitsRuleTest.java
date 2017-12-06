package io.github.anderscheow.validator.rules.regex;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DigitsRuleTest {

    private DigitsRule digitsRule;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        digitsRule = null;
    }

    @Test(expected = NullPointerException.class)
    public void validate_EmptySample_ThrowNullPointerException() throws Exception {
        digitsRule = new DigitsRule();

        digitsRule.validate(null);
    }

    @Test
    public void validate_SampleWithDigitsOnly_ReturnTrue() throws Exception {
        digitsRule = new DigitsRule();

        String sample = "123";

        assertTrue(digitsRule.validate(sample));
    }

    @Test
    public void validate_SampleWithAlphanumeric_ReturnFalse() throws Exception {
        digitsRule = new DigitsRule();

        String sample = "abc123";

        assertFalse(digitsRule.validate(sample));
    }

    @Test
    public void validate_SampleWithAlphabetOnly_ReturnFalse() throws Exception {
        digitsRule = new DigitsRule();

        String sample = "abc";

        assertFalse(digitsRule.validate(sample));
    }

    @Test
    public void validate_SampleWithDigitsAndSymbols_ReturnFalse() throws Exception {
        digitsRule = new DigitsRule();

        String sample = "100,000,000";

        assertFalse(digitsRule.validate(sample));
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = "Value does not match digits regex";

        digitsRule = new DigitsRule();

        assertEquals(errorMessage, digitsRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        digitsRule = new DigitsRule(errorMessage);

        assertEquals(errorMessage, digitsRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        digitsRule = new DigitsRule(errorRes);

        assertEquals(errorRes, digitsRule.errorRes());
    }
}