package io.github.anderscheow.validator.rules;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.github.anderscheow.validator.util.ErrorMessage;
import io.github.anderscheow.validator.util.Validate;

public abstract class BaseRule extends ErrorMessage implements Validate {

    private int errorRes;

    private String errorMessage;

    public BaseRule() {}

    public BaseRule(@StringRes int errorRes) {
        this.errorRes = errorRes;
    }

    public BaseRule(@NonNull String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public int errorRes() {
        return errorRes;
    }

    @NonNull
    @Override
    public String errorMessage() {
        return errorMessage;
    }
}
