package io.github.anderscheow.validator.rules;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public abstract class BaseRule {

    public abstract boolean validate(String value);

    @StringRes
    public int errorRes() {
        return -1;
    }

    @NonNull
    public  String errorMessage() {
        return "Invalid input";
    }
}
