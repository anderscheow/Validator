package io.github.anderscheow.validator.conditions.common;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.github.anderscheow.validator.conditions.Condition;
import io.github.anderscheow.validator.rules.common.MaxRule;
import io.github.anderscheow.validator.rules.common.MinRule;
import io.github.anderscheow.validator.rules.regex.DigitsRule;

import static org.junit.Assert.*;

public class AndTest {

    private Condition and;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        and = null;
    }

    @Test
    public void validate_MatchThreeConditionsOutOfThree_ReturnTrue() throws Exception {
        and = new And()
                .add(new MinRule(5)) // match
                .add(new MaxRule(10)) // match
                .add(new DigitsRule()); // match

        String sample = "1234567";

        assertTrue(and.validate(sample));
    }

    @Test
    public void validate_MatchTwoConditionsOutOfThree_ReturnFalse() throws Exception {
        and = new And()
                .add(new MinRule(5)) // A
                .add(new MaxRule(10)) // B
                .add(new DigitsRule()); // C

        String[] samples = new String[] {
                "abcde", // Match A,B
                "12345678901", // Match A,C
                "123", // Match B,C
        };

        for (String sample : samples) {
            assertFalse("This sample failed: " + sample, and.validate(sample));
        }
    }

    @Test
    public void validate_MatchOneConditionOutOfThree_ReturnFalse() throws Exception {
        and = new And()
                .add(new MinRule(5)) // A
                .add(new MaxRule(10)) // B
                .add(new DigitsRule()); // C

        String[] samples = new String[] {
                "abc 12", // Match A
                "test-123", // Match B
                "123456789012345", // Match C
        };

        for (String sample : samples) {
            assertFalse("This sample failed: " + sample, and.validate(sample));
        }
    }

    @Test
    public void validate_MatchZeroConditionOutOfThree_ReturnFalse() throws Exception {
        and = new And()
                .add(new MinRule(5)) // A
                .add(new MaxRule(10)) // B
                .add(new DigitsRule()); // C

        String[] samples = new String[] {
                "testing 123, how are you"
        };

        for (String sample : samples) {
            assertFalse("This sample failed: " + sample, and.validate(sample));
        }
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = "Does not match 'And' condition";

        and = new And();

        assertEquals(errorMessage, and.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        and = new And(errorMessage);

        assertEquals(errorMessage, and.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        and = new And(errorRes);

        assertEquals(errorRes, and.errorRes());
    }
}