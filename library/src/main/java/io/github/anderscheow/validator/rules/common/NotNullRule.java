package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.github.anderscheow.validator.rules.BaseRule;

public class NotNullRule extends BaseRule {

    public NotNullRule() {}

    public NotNullRule(@StringRes int errorRes) {
        super(errorRes);
    }

    public NotNullRule(@NonNull String errorMessage) {
        super(errorMessage);
    }

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
