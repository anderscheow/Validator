package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;

import java.util.Locale;

import io.github.anderscheow.validator.rules.BaseRule;

public class MaxRule extends BaseRule {

    private int maxValue;

    public MaxRule(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public boolean validate(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        return value.length() <= maxValue;
    }

    @NonNull
    @Override
    public String errorMessage() {
        return String.format(Locale.getDefault(), "Length must not exceed %d characters", maxValue);
    }
}
