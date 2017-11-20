package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;

import io.github.anderscheow.validator.rules.BaseRule;

public class NotEmptyRule extends BaseRule {

    @Override
    public boolean validate(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        return !value.isEmpty();
    }

    @NonNull
    @Override
    public String errorMessage() {
        return "Value must not be empty";
    }
}
