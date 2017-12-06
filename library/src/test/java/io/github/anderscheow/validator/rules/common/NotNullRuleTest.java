package io.github.anderscheow.validator.rules.common;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

public class NotNullRuleTest {

    private NotNullRule notNullRule;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        notNullRule = null;
    }

    @Test
    public void validate_ValidSample_ReturnTrue() throws Exception {
        notNullRule = new NotNullRule();

        String sample = "test";

        assertTrue(notNullRule.validate(sample));
    }

    @Test
    public void validate_InvalidSample_ReturnFalse() throws Exception {
        notNullRule = new NotNullRule();

        assertFalse(notNullRule.validate(null));
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = "Value must not be null";

        notNullRule = new NotNullRule();

        assertEquals(errorMessage, notNullRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        notNullRule = new NotNullRule(errorMessage);

        assertEquals(errorMessage, notNullRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        notNullRule = new NotNullRule(errorRes);

        assertEquals(errorRes, notNullRule.errorRes());
    }
}