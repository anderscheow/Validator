package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;

import io.github.anderscheow.validator.rules.BaseRule;

public class NotNullRule extends BaseRule {

    @Override
    public boolean validate(String value) {
        return value != null;
    }

    @NonNull
    @Override
    public String errorMessage() {
        return "Value must not be null";
    }
}
