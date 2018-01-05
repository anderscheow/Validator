package io.github.anderscheow.validator.conditions;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.github.anderscheow.validator.rules.common.MaxRule;
import io.github.anderscheow.validator.rules.common.MinRule;

import static org.junit.Assert.*;

public class ConditionTest {

    private Condition condition;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        condition = null;
    }

    @Test
    public void add_OneBaseRule() throws Exception {
        condition = new Condition() {
            @Override
            public boolean validate(Object value) {
                return false;
            }
        }.add(new MinRule(5));

        assertEquals(1, condition.getBaseRules().size());
    }

    @Test
    public void add_TwoBaseRule() throws Exception {
        condition = new Condition() {
            @Override
            public boolean validate(Object value) {
                return false;
            }
        }.add(new MinRule(5))
            .add(new MaxRule(10));

        assertEquals(2, condition.getBaseRules().size());
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = "Invalid input";

        condition = new Condition() {
            @Override
            public boolean validate(Object value) {
                return false;
            }
        };

        assertEquals(errorMessage, condition.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        condition = new Condition(errorMessage) {
            @Override
            public boolean validate(Object value) {
                return false;
            }
        };

        assertEquals(errorMessage, condition.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        condition = new Condition(errorRes) {
            @Override
            public boolean validate(Object value) {
                return false;
            }
        };

        assertEquals(errorRes, condition.errorRes());
    }
}