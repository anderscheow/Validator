package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import java.util.Locale;

import io.github.anderscheow.validator.rules.BaseRule;

public class LengthRule extends BaseRule {

    private int minLength;
    private int maxLength;

    public LengthRule(int minLength, int maxLength) {
        super(String.format(Locale.getDefault(), "Length must be between %d and %d", minLength, maxLength));
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public LengthRule(int minLength, int maxLength, @StringRes int errorRes) {
        super(errorRes);
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public LengthRule(int minLength, int maxLength, @NonNull String errorMessage) {
        super(errorMessage);
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public boolean validate(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

        if (value instanceof String) {
            assertMinMax(minLength, maxLength);

            int length = ((String) value).length();

            boolean isMinValid = true;
            if (minLength != Integer.MIN_VALUE) {
                isMinValid = length >= minLength;
            }

            boolean isMaxValid = true;
            if (maxLength != Integer.MAX_VALUE) {
                isMaxValid = length <= maxLength;
            }

            return isMinValid && isMaxValid;
        }

        throw new ClassCastException("Required String value");
    }

    private void assertMinMax(int min, int max) {
        if (min > max) {
            String message = String.format(Locale.getDefault(), "'minLength' (%d) " +
                    "should be smaller than 'maxLength' (%d)", min, max);
            throw new IllegalStateException(message);
        }
    }
}
