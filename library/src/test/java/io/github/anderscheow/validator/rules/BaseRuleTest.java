package io.github.anderscheow.validator.rules;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.github.anderscheow.validator.conditions.common.And;

import static org.junit.Assert.*;

public class BaseRuleTest {

    private BaseRule baseRule;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        baseRule = null;
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = "Invalid input";

        baseRule = new BaseRule() {
            @Override
            public boolean validate(String value) {
                return false;
            }
        };

        assertEquals(errorMessage, baseRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        baseRule = new BaseRule(errorMessage) {
            @Override
            public boolean validate(String value) {
                return false;
            }
        };

        assertEquals(errorMessage, baseRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        baseRule = new BaseRule(errorRes) {
            @Override
            public boolean validate(String value) {
                return false;
            }
        };

        assertEquals(errorRes, baseRule.errorRes());
    }
}