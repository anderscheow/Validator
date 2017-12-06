package io.github.anderscheow.validator.rules.regex;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailRuleTest {

    private EmailRule emailRule;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        emailRule = null;
    }

    @Test(expected = NullPointerException.class)
    public void validate_EmptySample_ThrowNullPointerException() throws Exception {
        emailRule = new EmailRule();

        emailRule.validate(null);
    }

    @Test
    public void validate_ValidSamples_ReturnTrue() throws Exception {
        emailRule = new EmailRule();

        String[] samples = new String[] {
                "test@hotmail.com",
                "test@hotmail.co.uk",
                "test@hotmail.my",
        };

        for (String sample : samples) {
            assertTrue("This email failed: " + sample, emailRule.validate(sample));
        }
    }

    @Test
    public void validate_InvalidSamples_ReturnFalse() throws Exception {
        emailRule = new EmailRule();

        String[] samples = new String[] {
                "test@hotmail.c",
                "test@hotmail.co.",
        };

        for (String sample : samples) {
            assertFalse("This email failed: " + sample, emailRule.validate(sample));
        }
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = "Value does not match email regex";

        emailRule = new EmailRule();

        assertEquals(errorMessage, emailRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        emailRule = new EmailRule(errorMessage);

        assertEquals(errorMessage, emailRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        emailRule = new EmailRule(errorRes);

        assertEquals(errorRes, emailRule.errorRes());
    }
}