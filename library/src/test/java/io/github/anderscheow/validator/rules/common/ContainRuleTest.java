package io.github.anderscheow.validator.rules.common;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ContainRuleTest {

    private static final String KEYWORD = "test";

    private ContainRule containRule;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        containRule = null;
    }

    @Test(expected = NullPointerException.class)
    public void validate_EmptySample_ThrowNullPointerException() throws Exception {
        containRule = new ContainRule(KEYWORD);

        containRule.validate(null);
    }

    @Test
    public void validate_ValidSample_ReturnTrue() throws Exception {
        containRule = new ContainRule(KEYWORD);

        String sample = "contain_test";

        assertTrue(containRule.validate(sample));
    }

    @Test
    public void validate_InvalidSample_ReturnFalse() throws Exception {
        containRule = new ContainRule(KEYWORD);

        String sample = "contain_TEST";

        assertFalse(containRule.validate(sample));
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = String.format(Locale.getDefault(), "Value does not contain '%s'", KEYWORD);

        containRule = new ContainRule(KEYWORD);

        assertEquals(errorMessage, containRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        containRule = new ContainRule(KEYWORD, errorMessage);

        assertEquals(errorMessage, containRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        containRule = new ContainRule(KEYWORD, errorRes);

        assertEquals(errorRes, containRule.errorRes());
    }
}