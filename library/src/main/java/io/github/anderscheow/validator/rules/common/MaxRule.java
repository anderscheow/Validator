package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import java.util.Locale;

import io.github.anderscheow.validator.rules.BaseRule;

public class MaxRule extends BaseRule {

    private int maxLength;

    public MaxRule(int maxLength) {
        super(String.format(Locale.getDefault(), "Length must not exceed %d characters", maxLength));
        this.maxLength = maxLength;
    }

    public MaxRule(int maxLength, @StringRes int errorRes) {
        super(errorRes);
        this.maxLength = maxLength;
    }

    public MaxRule(int maxLength, @NonNull String errorMessage) {
        super(errorMessage);
        this.maxLength = maxLength;
    }

    @Override
    public boolean validate(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

        if (value instanceof String) {
            return ((String) value).length() <= maxLength;
        }

        throw new ClassCastException("Required String value");
    }
}
