package io.github.anderscheow.validator.rules.regex;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordRuleTest {

    private PasswordRule passwordRule;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        passwordRule = null;
    }

    @Test(expected = NullPointerException.class)
    public void validate_EmptySample_ThrowNullPointerException() throws Exception {
        passwordRule = new PasswordRule(PasswordRule.PasswordRegex.ANY);

        passwordRule.validate(null);
    }

    @Test
    public void validate_AnySamplesWithAnyRegex_ReturnTrue() throws Exception {
        passwordRule = new PasswordRule(PasswordRule.PasswordRegex.ANY);

        String[] samples = new String[] {
                "abc ABC 123 .,/",
                "abc123,./",
                "abcABC123",
                "abcABC,./",
                "ABC123,./",
                "abcABC",
                "abc123",
                "abc,./",
                "ABC123",
                "ABC,./",
                "123,./",
                "abc",
                "ABC",
                "123",
                ",./",
                " ",
        };

        for (String sample : samples) {
            assertTrue("This password failed: " + sample, passwordRule.validate(sample));
        }
    }

    @Test
    public void validate_ValidSamplesWithAlphaRegex_ReturnTrue() throws Exception {
        passwordRule = new PasswordRule(PasswordRule.PasswordRegex.ALPHA);

        String[] samples = new String[] {
                "abcABC123",
                "abcABC",
                "abc123",
                "ABC123",
                "abc",
                "ABC",
                "123",
        };

        for (String sample : samples) {
            assertTrue("This password failed: " + sample, passwordRule.validate(sample));
        }
    }

    @Test
    public void validate_InvalidSamplesWithAlphaRegex_ReturnFalse() throws Exception {
        passwordRule = new PasswordRule(PasswordRule.PasswordRegex.ALPHA);

        String[] samples = new String[] {
                "abc ABC 123 .,/",
                "abc123,./",
                "abcABC,./",
                "ABC123,./",
                "abc,./",
                "ABC,./",
                "123,./",
                ",./",
                " ",
        };

        for (String sample : samples) {
            assertFalse("This password failed: " + sample, passwordRule.validate(sample));
        }
    }

    @Test
    public void validate_ValidSamplesWithAlphaMixedCaseRegex_ReturnTrue() throws Exception {
        passwordRule = new PasswordRule(PasswordRule.PasswordRegex.ALPHA_MIXED_CASE);

        String[] samples = new String[] {
                "abc ABC 123 .,/",
                "abcABC123",
                "abcABC,./",
                "abcABC",
        };

        for (String sample : samples) {
            assertTrue("This password failed: " + sample, passwordRule.validate(sample));
        }
    }

    @Test
    public void validate_InvalidSamplesWithAlphaMixedCaseRegex_ReturnFalse() throws Exception {
        passwordRule = new PasswordRule(PasswordRule.PasswordRegex.ALPHA_MIXED_CASE);

        String[] samples = new String[] {
                "abc123,./",
                "ABC123,./",
                "abc123",
                "abc,./",
                "ABC123",
                "ABC,./",
                "123,./",
                "abc",
                "ABC",
                "123",
                ",./",
                " ",
        };

        for (String sample : samples) {
            assertFalse("This password failed: " + sample, passwordRule.validate(sample));
        }
    }

    @Test
    public void validate_ValidSamplesWithNumericRegex_ReturnTrue() throws Exception {
        passwordRule = new PasswordRule(PasswordRule.PasswordRegex.NUMERIC);

        String[] samples = new String[] {
                "123",
        };

        for (String sample : samples) {
            assertTrue("This password failed: " + sample, passwordRule.validate(sample));
        }
    }

    @Test
    public void validate_InvalidSamplesWithNumericRegex_ReturnFalse() throws Exception {
        passwordRule = new PasswordRule(PasswordRule.PasswordRegex.NUMERIC);

        String[] samples = new String[] {
                "abc ABC 123 .,/",
                "abc123,./",
                "abcABC123",
                "abcABC,./",
                "ABC123,./",
                "abcABC",
                "abc123",
                "abc,./",
                "ABC123",
                "ABC,./",
                "123,./",
                "abc",
                "ABC",
                ",./",
                " ",
        };

        for (String sample : samples) {
            assertFalse("This password failed: " + sample, passwordRule.validate(sample));
        }
    }

    @Test
    public void validate_ValidSamplesWithAlphaNumericRegex_ReturnTrue() throws Exception {
        passwordRule = new PasswordRule(PasswordRule.PasswordRegex.ALPHA_NUMERIC);

        String[] samples = new String[] {
                "abc ABC 123 .,/",
                "abc123,./",
                "abcABC123",
                "ABC123,./",
                "abc123",
                "ABC123",
        };

        for (String sample : samples) {
            assertTrue("This password failed: " + sample, passwordRule.validate(sample));
        }
    }

    @Test
    public void validate_InvalidSamplesWithAlphaNumericRegex_ReturnFalse() throws Exception {
        passwordRule = new PasswordRule(PasswordRule.PasswordRegex.ALPHA_NUMERIC);

        String[] samples = new String[] {
                "abcABC,./",
                "abcABC",
                "abc,./",
                "ABC,./",
                "123,./",
                "abc",
                "ABC",
                "123",
                ",./",
                " ",
        };

        for (String sample : samples) {
            assertFalse("This password failed: " + sample, passwordRule.validate(sample));
        }
    }

    @Test
    public void validate_ValidSamplesWithAlphaNumericSymbolsRegex_ReturnTrue() throws Exception {
        passwordRule = new PasswordRule(PasswordRule.PasswordRegex.ALPHA_NUMERIC_SYMBOLS);

        String[] samples = new String[] {
                "abc ABC 123 .,/",
                "abc123,./",
                "ABC123,./",
        };

        for (String sample : samples) {
            assertTrue("This password failed: " + sample, passwordRule.validate(sample));
        }
    }

    @Test
    public void validate_InvalidSamplesWithAlphaNumericSymbolsRegex_ReturnFalse() throws Exception {
        passwordRule = new PasswordRule(PasswordRule.PasswordRegex.ALPHA_NUMERIC_SYMBOLS);

        String[] samples = new String[] {
                "abcABC123",
                "abcABC,./",
                "abcABC",
                "abc123",
                "abc,./",
                "ABC123",
                "ABC,./",
                "123,./",
                "abc",
                "ABC",
                "123",
                ",./",
                " ",
        };

        for (String sample : samples) {
            assertFalse("This password failed: " + sample, passwordRule.validate(sample));
        }
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = "Value does not match any password regex";

        passwordRule = new PasswordRule(PasswordRule.PasswordRegex.ANY);

        assertEquals(errorMessage, passwordRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        passwordRule = new PasswordRule(PasswordRule.PasswordRegex.ANY, errorMessage);

        assertEquals(errorMessage, passwordRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        passwordRule = new PasswordRule(PasswordRule.PasswordRegex.ANY, errorRes);

        assertEquals(errorRes, passwordRule.errorRes());
    }
}