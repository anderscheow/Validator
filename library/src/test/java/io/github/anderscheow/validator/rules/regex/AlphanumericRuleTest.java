package io.github.anderscheow.validator.rules.regex;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlphanumericRuleTest {

    private AlphanumericRule alphanumericRule;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        alphanumericRule = null;
    }

    @Test(expected = NullPointerException.class)
    public void validate_EmptySample_ThrowNullPointerException() throws Exception {
        alphanumericRule = new AlphanumericRule();

        alphanumericRule.validate(null);
    }

    @Test
    public void validate_SampleWithAlphanumeric_ReturnTrue() throws Exception {
        alphanumericRule = new AlphanumericRule();

        String sample = "test123";

        assertTrue(alphanumericRule.validate(sample));
    }

    @Test
    public void validate_SampleWithAlphanumericAndSpace_ReturnFalse() throws Exception {
        alphanumericRule = new AlphanumericRule();

        String sample = "test 123";

        assertFalse(alphanumericRule.validate(sample));
    }

    @Test
    public void validate_SampleWithAlphanumericAndSymbols_ReturnFalse() throws Exception {
        alphanumericRule = new AlphanumericRule();

        String sample = "test 100,000,000";

        assertFalse(alphanumericRule.validate(sample));
    }

    @Test
    public void validate_SampleWithAlphabetOnly_ReturnTrue() throws Exception {
        alphanumericRule = new AlphanumericRule();

        String sample = "test";

        assertTrue(alphanumericRule.validate(sample));
    }

    @Test
    public void validate_SampleWithDigitsOnly_ReturnTrue() throws Exception {
        alphanumericRule = new AlphanumericRule();

        String sample = "123";

        assertTrue(alphanumericRule.validate(sample));
    }

    @Test
    public void validate_SampleWithSymbolsOnly_ReturnFalse() throws Exception {
        alphanumericRule = new AlphanumericRule();

        String sample = ",./";

        assertFalse(alphanumericRule.validate(sample));
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = "Value does not match alphanumeric regex";

        alphanumericRule = new AlphanumericRule();

        assertEquals(errorMessage, alphanumericRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        alphanumericRule = new AlphanumericRule(errorMessage);

        assertEquals(errorMessage, alphanumericRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        alphanumericRule = new AlphanumericRule(errorRes);

        assertEquals(errorRes, alphanumericRule.errorRes());
    }
}