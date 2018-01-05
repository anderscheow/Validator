package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import java.util.Locale;

import io.github.anderscheow.validator.rules.BaseRule;

public class NotEqualRule extends BaseRule {

    private String keyword;

    public NotEqualRule(String keyword) {
        super(String.format(Locale.getDefault(), "Value equal to '%s'", keyword));
        this.keyword = keyword;
    }

    public NotEqualRule(String keyword, @StringRes int errorRes) {
        super(errorRes);
        this.keyword = keyword;
    }

    public NotEqualRule(String keyword, @NonNull String errorMessage) {
        super(errorMessage);
        this.keyword = keyword;
    }

    @Override
    public boolean validate(Object value) {
        if (value instanceof String) {
            return !value.equals(keyword);
        }

        throw new ClassCastException("Required String value");
    }
}
