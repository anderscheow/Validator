package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;

import java.util.Locale;

import io.github.anderscheow.validator.rules.BaseRule;

public class NotEqualRule extends BaseRule {

    private String keyword;

    public NotEqualRule(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean validate(String value) {
        return !value.equals(keyword);
    }

    @NonNull
    @Override
    public String errorMessage() {
        return String.format(Locale.getDefault(), "Value equal to '%s'", keyword);
    }
}
