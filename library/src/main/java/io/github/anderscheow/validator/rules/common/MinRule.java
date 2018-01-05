package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import java.util.Locale;

import io.github.anderscheow.validator.rules.BaseRule;

public class MinRule extends BaseRule {

    private int minLength;

    public MinRule(int minLength) {
        super(String.format(Locale.getDefault(), "Length must exceed at least %d characters", minLength));
        this.minLength = minLength;
    }

    public MinRule(int minLength, @StringRes int errorRes) {
        super(errorRes);
        this.minLength = minLength;
    }

    public MinRule(int minLength, @NonNull String errorMessage) {
        super(errorMessage);
        this.minLength = minLength;
    }

    @Override
    public boolean validate(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

        if (value instanceof String) {
            return ((String) value).length() >= minLength;
        }

        throw new ClassCastException("Required String value");
    }
}
