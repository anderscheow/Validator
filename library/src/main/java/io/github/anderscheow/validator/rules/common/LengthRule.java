package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import java.util.Locale;

import io.github.anderscheow.validator.rules.BaseRule;

public class LengthRule extends BaseRule {

    private int minValue;
    private int maxValue;

    public LengthRule(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public LengthRule(int minValue, int maxValue, @StringRes int errorRes) {
        super(errorRes);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public LengthRule(int minValue, int maxValue, @NonNull String errorMessage) {
        super(errorMessage);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public boolean validate(String value) {
        if (value == null) {
            throw new NullPointerException();
        }

        assertMinMax(minValue, maxValue);

        int length = value.length();

        boolean isMinValid = true;
        if (minValue != Integer.MIN_VALUE) {
            isMinValid = length >= minValue;
        }

        boolean isMaxValid = true;
        if (maxValue != Integer.MAX_VALUE) {
            isMaxValid = length <= maxValue;
        }

        return isMinValid && isMaxValid;
    }

    private void assertMinMax(int min, int max) {
        if (min > max) {
            String message = String.format(Locale.getDefault(), "'minValue' (%d) " +
                    "should be smaller than 'maxValue' (%d)", min, max);
            throw new IllegalStateException(message);
        }
    }

    @NonNull
    @Override
    public String errorMessage() {
        return String.format(Locale.getDefault(), "Length must be between %d and %d", minValue, maxValue);
    }
}
