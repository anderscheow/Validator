package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;

import java.util.Locale;

import io.github.anderscheow.validator.rules.BaseRule;

public class NotContainRule extends BaseRule {

    private String keyword;

    public NotContainRule(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean validate(String value) {
        return !value.contains(keyword);
    }

    @NonNull
    @Override
    public String errorMessage() {
        return String.format(Locale.getDefault(), "Value does contain '%s'", keyword);
    }
}
