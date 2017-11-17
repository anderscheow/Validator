package io.github.anderscheow.validator.rules;

import android.support.annotation.StringRes;

public interface BaseRule {

    boolean validate(String s);

    @StringRes
    int errorMessage();
}
