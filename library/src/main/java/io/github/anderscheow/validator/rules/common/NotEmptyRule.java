package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.github.anderscheow.validator.rules.BaseRule;

public class NotEmptyRule extends BaseRule {

    public NotEmptyRule() {
        super("Value must not be empty");
    }

    public NotEmptyRule(@StringRes int errorRes) {
        super(errorRes);
    }

    public NotEmptyRule(@NonNull String errorMessage) {
        super(errorMessage);
    }

    @Override
    public boolean validate(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

        if (value instanceof String) {
            return !((String) value).isEmpty();
        }

        throw new ClassCastException("Required String value");
    }
}
