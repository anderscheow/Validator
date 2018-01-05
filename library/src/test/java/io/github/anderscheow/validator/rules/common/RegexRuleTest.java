package io.github.anderscheow.validator.rules.common;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

public class RegexRuleTest {

    private static final String REGEX = "\\d+";

    private RegexRule regexRule;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        regexRule = null;
    }

    @Test(expected = NullPointerException.class)
    public void validate_EmptySample_ThrowNullPointerException() throws Exception {
        regexRule = new RegexRule(REGEX) {};

        regexRule.validate(null);
    }

    @Test(expected = ClassCastException.class)
    public void validate_NotStringSample_ThrowClassCastException() throws Exception {
        regexRule = new RegexRule(REGEX) {};

        // Any value other than String
        regexRule.validate(123);
    }

    @Test
    public void validate_ValidSample_ReturnTrue() throws Exception {
        regexRule = new RegexRule(REGEX) {};

        String sample = "123";

        assertTrue(regexRule.validate(sample));
    }

    @Test
    public void validate_InvalidSample_ReturnFalse() throws Exception {
        regexRule = new RegexRule(REGEX) {};

        String sample = "TEST";

        assertFalse(regexRule.validate(sample));
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = "Does not match regex rule";

        regexRule = new RegexRule(REGEX) {};

        assertEquals(errorMessage, regexRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        regexRule = new RegexRule(REGEX, errorMessage) {};

        assertEquals(errorMessage, regexRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        regexRule = new RegexRule(REGEX, errorRes) {};

        assertEquals(errorRes, regexRule.errorRes());
    }
}