package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.github.anderscheow.validator.rules.BaseRule;

public class NotNullRule extends BaseRule {

    public NotNullRule() {
        super("Value must not be null");
    }

    public NotNullRule(@StringRes int errorRes) {
        super(errorRes);
    }

    public NotNullRule(@NonNull String errorMessage) {
        super(errorMessage);
    }

    @Override
    public boolean validate(Object value) {
        return value != null;
    }
}
