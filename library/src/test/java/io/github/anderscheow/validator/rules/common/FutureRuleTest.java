package io.github.anderscheow.validator.rules.common;

import android.support.annotation.StringRes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FutureRuleTest {

    private static final DateFormat VALID_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private FutureRule futureRule;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        futureRule = null;
    }

    @Test(expected = NullPointerException.class)
    public void validate_EmptySample_ThrowNullPointerException() throws Exception {
        futureRule = new FutureRule(VALID_DATE_FORMAT);

        futureRule.validate(null);
    }

    @Test(expected = NullPointerException.class)
    public void validate_EmptyDateFormat_ThrowNullPointerException() throws Exception {
        futureRule = new FutureRule(null);

        Calendar calendar = Calendar.getInstance();

        String sample = VALID_DATE_FORMAT.format(calendar.getTime());

        futureRule.validate(sample);
    }

    @Test(expected = ClassCastException.class)
    public void validate_NotStringOrDateSample_ThrowClassCastException() throws Exception {
        futureRule = new FutureRule(VALID_DATE_FORMAT);

        // Any value other than String or Date
        futureRule.validate(123);
    }

    @Test
    public void validate_ValidDateFormatAndValidValue_ReturnTrue() throws Exception {
        futureRule = new FutureRule(VALID_DATE_FORMAT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        String sample = VALID_DATE_FORMAT.format(calendar.getTime());

        assertTrue(futureRule.validate(sample));
    }

    @Test
    public void validate_ValidDateValue_ReturnTrue() throws Exception {
        futureRule = new FutureRule(VALID_DATE_FORMAT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        Date sample = calendar.getTime();

        assertTrue(futureRule.validate(sample));
    }

    @Test
    public void validate_ValidDateFormatAndInvalidValue_ReturnFalse() throws Exception {
        futureRule = new FutureRule(VALID_DATE_FORMAT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        String sample = VALID_DATE_FORMAT.format(calendar.getTime());

        assertFalse(futureRule.validate(sample));
    }

    @Test
    public void validate_InvalidDateValue_ReturnFalse() throws Exception {
        futureRule = new FutureRule(VALID_DATE_FORMAT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        Date sample = calendar.getTime();

        assertFalse(futureRule.validate(sample));
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_InvalidDateFormatAndValidValue_ThrowIllegalArgumentException() throws Exception {
        futureRule = new FutureRule(new SimpleDateFormat("abc/dd/ee"));

        String sample = "5/12/2017";

        futureRule.validate(sample);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_InvalidDateFormatAndInvalidValue_ThrowIllegalArgumentException() throws Exception {
        futureRule = new FutureRule(new SimpleDateFormat("abc/dd/ee"));

        String sample = "100/100/2017";

        futureRule.validate(sample);
    }

    @Test
    public void errorMessage_DefaultErrorMessage() throws Exception {
        String errorMessage = "Does not match future rule";

        futureRule = new FutureRule(VALID_DATE_FORMAT);

        assertEquals(errorMessage, futureRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorMessage() throws Exception {
        String errorMessage = "This is a custom error message";

        futureRule = new FutureRule(VALID_DATE_FORMAT, errorMessage);

        assertEquals(errorMessage, futureRule.errorMessage());
    }

    @Test
    public void errorMessage_CustomErrorRes() throws Exception {
        @StringRes int errorRes = 0;

        futureRule = new FutureRule(VALID_DATE_FORMAT, errorRes);

        assertEquals(errorRes, futureRule.errorRes());
    }
}