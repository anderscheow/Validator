package io.github.anderscheow.validator.rules;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.github.anderscheow.validator.util.ErrorMessage;
import io.github.anderscheow.validator.util.Validate;

public abstract class BaseRule extends ErrorMessage implements Validate {

    public BaseRule() {}

    public BaseRule(@StringRes int errorRes) {
        setErrorRes(errorRes);
    }

    public BaseRule(@NonNull String errorMessage) {
        setErrorMessage(errorMessage);
    }
}
