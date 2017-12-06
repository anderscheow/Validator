package io.github.anderscheow.validator.rules.regex;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreditCardRuleTest {

    private CreditCardRule creditCardRule;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        creditCardRule = null;
    }

    @Test(expected = NullPointerException.class)
    public void validate_EmptySample_ThrowNullPointerException() throws Exception {
        creditCardRule = new CreditCardRule(CreditCardRule.CreditCardRegex.VISA);

        creditCardRule.validate(null);
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = "Value does not match any credit card regex";

        creditCardRule = new CreditCardRule(CreditCardRule.CreditCardRegex.VISA);

        assertEquals(errorMessage, creditCardRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        creditCardRule = new CreditCardRule(CreditCardRule.CreditCardRegex.VISA, errorMessage);

        assertEquals(errorMessage, creditCardRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        creditCardRule = new CreditCardRule(CreditCardRule.CreditCardRegex.VISA, errorRes);

        assertEquals(errorRes, creditCardRule.errorRes());
    }
}