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
    public void validate_VisaSamples_ReturnTrue() throws Exception {
        creditCardRule = new CreditCardRule(CreditCardRule.CreditCardRegex.VISA);

        String[] samples = new String[] {
                "4111111111111111",
                "4012888888881881",
                "4222222222222",
        };

        for (String sample : samples) {
            assertTrue("This sample failed: " + sample, creditCardRule.validate(sample));
        }
    }

    @Test
    public void validate_MasterCardSamples_ReturnTrue() throws Exception {
        creditCardRule = new CreditCardRule(CreditCardRule.CreditCardRegex.MASTERCARD);

        String[] samples = new String[] {
                "5555555555554444",
                "5105105105105100",
        };

        for (String sample : samples) {
            assertTrue("This sample failed: " + sample, creditCardRule.validate(sample));
        }
    }

    @Test
    public void validate_AmericanExpressSamples_ReturnTrue() throws Exception {
        creditCardRule = new CreditCardRule(CreditCardRule.CreditCardRegex.AMERICAN_EXPRESS);

        String[] samples = new String[] {
                "378282246310005",
                "371449635398431",
                "378734493671000",
        };

        for (String sample : samples) {
            assertTrue("This sample failed: " + sample, creditCardRule.validate(sample));
        }
    }

    @Test
    public void validate_DinersClubSamples_ReturnTrue() throws Exception {
        creditCardRule = new CreditCardRule(CreditCardRule.CreditCardRegex.DINERS_CLUB);

        String[] samples = new String[] {
                "30569309025904",
                "38520000023237",
        };

        for (String sample : samples) {
            assertTrue("This sample failed: " + sample, creditCardRule.validate(sample));
        }
    }

    @Test
    public void validate_DiscoverSamples_ReturnTrue() throws Exception {
        creditCardRule = new CreditCardRule(CreditCardRule.CreditCardRegex.DISCOVER);

        String[] samples = new String[] {
                "6011111111111117",
                "6011000990139424",
        };

        for (String sample : samples) {
            assertTrue("This sample failed: " + sample, creditCardRule.validate(sample));
        }
    }

    @Test
    public void validate_JcbSamples_ReturnTrue() throws Exception {
        creditCardRule = new CreditCardRule(CreditCardRule.CreditCardRegex.JCB);

        String[] samples = new String[] {
                "3530111333300000",
                "3566002020360505",
        };

        for (String sample : samples) {
            assertTrue("This sample failed: " + sample, creditCardRule.validate(sample));
        }
    }

    @Test
    public void validate_ChinaUnionPaySamples_ReturnTrue() throws Exception {
        creditCardRule = new CreditCardRule(CreditCardRule.CreditCardRegex.CHINA_UNION_PAY);

        String[] samples = new String[] {
                "6276028610651858",
                "6223263470710627",
                "6270213860867471",
        };

        for (String sample : samples) {
            assertTrue("This sample failed: " + sample, creditCardRule.validate(sample));
        }
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