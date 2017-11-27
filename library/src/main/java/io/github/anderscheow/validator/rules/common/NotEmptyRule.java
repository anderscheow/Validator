package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.github.anderscheow.validator.rules.BaseRule;

public class NotEmptyRule extends BaseRule {

    public NotEmptyRule() {}

    public NotEmptyRule(@StringRes int errorRes) {
        super(errorRes);
    }

    public NotEmptyRule(@NonNull String errorMessage) {
        super(errorMessage);
    }

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
