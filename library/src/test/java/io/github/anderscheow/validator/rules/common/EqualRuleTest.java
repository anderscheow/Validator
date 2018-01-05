package io.github.anderscheow.validator.rules.common;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EqualRuleTest {

    private static final String KEYWORD = "test";

    private EqualRule equalRule;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        equalRule = null;
    }

    @Test(expected = ClassCastException.class)
    public void validate_NotStringSample_ThrowClassCastException() throws Exception {
        equalRule = new EqualRule(KEYWORD);

        // Any value other than String
        equalRule.validate(123);
    }

    @Test
    public void validate_ValidSample_ReturnTrue() throws Exception {
        equalRule = new EqualRule(KEYWORD);

        String sample = "test";

        assertTrue(equalRule.validate(sample));
    }

    @Test
    public void validate_InvalidSample_ReturnFalse() throws Exception {
        equalRule = new EqualRule(KEYWORD);

        String sample = "TEST";

        assertFalse(equalRule.validate(sample));
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = String.format(Locale.getDefault(), "Value does not equal to '%s'", KEYWORD);

        equalRule = new EqualRule(KEYWORD);

        assertEquals(errorMessage, equalRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        equalRule = new EqualRule(KEYWORD, errorMessage);

        assertEquals(errorMessage, equalRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        equalRule = new EqualRule(KEYWORD, errorRes);

        assertEquals(errorRes, equalRule.errorRes());
    }
}