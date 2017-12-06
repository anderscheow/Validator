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

public class OrTest {
    
    private Condition or;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        or = null;
    }

    @Test
    public void validate_MatchThreeConditionsOutOfThree_ReturnTrue() throws Exception {
        or = new Or()
                .add(new MinRule(5)) // match
                .add(new MaxRule(10)) // match
                .add(new DigitsRule()); // match

        String sample = "1234567";

        assertTrue(or.validate(sample));
    }

    @Test
    public void validate_MatchTwoConditionsOutOfThree_ReturnTrue() throws Exception {
        or = new Or()
                .add(new MinRule(5)) // A
                .add(new MaxRule(10)) // B
                .add(new DigitsRule()); // C

        String[] samples = new String[] {
                "abcde", // Match A,B
                "12345678901", // Match A,C
                "123", // Match B,C
        };

        for (String sample : samples) {
            assertTrue("This sample failed: " + sample, or.validate(sample));
        }
    }

    @Test
    public void validate_MatchOneConditionOutOfThree_ReturnTrue() throws Exception {
        or = new Or()
                .add(new MinRule(5)) // A
                .add(new MaxRule(10)) // B
                .add(new DigitsRule()); // C

        String[] samples = new String[] {
                "abc 12", // Match A
                "test-123", // Match B
                "123456789012345", // Match C
        };

        for (String sample : samples) {
            assertTrue("This sample failed: " + sample, or.validate(sample));
        }
    }

    @Test
    public void validate_MatchZeroConditionOutOfThree_ReturnFalse() throws Exception {
        or = new Or()
                .add(new MaxRule(10)) // A
                .add(new DigitsRule()); // B

        String[] samples = new String[] {
                "testing 123, how are you"
        };

        for (String sample : samples) {
            assertFalse("This sample failed: " + sample, or.validate(sample));
        }
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = "Does not match 'Or' condition";

        or = new Or();

        assertEquals(errorMessage, or.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        or = new Or(errorMessage);

        assertEquals(errorMessage, or.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        or = new Or(errorRes);

        assertEquals(errorRes, or.errorRes());
    }
}