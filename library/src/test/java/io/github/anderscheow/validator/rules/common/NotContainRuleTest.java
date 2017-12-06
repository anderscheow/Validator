package io.github.anderscheow.validator.rules.common;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

public class NotContainRuleTest {

    private static final String KEYWORD = "test";

    private NotContainRule notContainRule;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        notContainRule = null;
    }

    @Test(expected = NullPointerException.class)
    public void validate_EmptySample_ThrowNullPointerException() throws Exception {
        notContainRule = new NotContainRule(KEYWORD);

        notContainRule.validate(null);
    }

    @Test
    public void validate_ValidSample_ReturnTrue() throws Exception {
        notContainRule = new NotContainRule(KEYWORD);

        String sample = "contain_TEST";

        assertTrue(notContainRule.validate(sample));
    }

    @Test
    public void validate_InvalidSample_ReturnFalse() throws Exception {
        notContainRule = new NotContainRule(KEYWORD);

        String sample = "contain_test";

        assertFalse(notContainRule.validate(sample));
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = String.format(Locale.getDefault(), "Value does contain '%s'", KEYWORD);

        notContainRule = new NotContainRule(KEYWORD);

        assertEquals(errorMessage, notContainRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        notContainRule = new NotContainRule(KEYWORD, errorMessage);

        assertEquals(errorMessage, notContainRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        notContainRule = new NotContainRule(KEYWORD, errorRes);

        assertEquals(errorRes, notContainRule.errorRes());
    }
}