package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;

import java.util.Locale;

import io.github.anderscheow.validator.rules.BaseRule;

public class ContainRule extends BaseRule {

    private String keyword;

    public ContainRule(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean validate(String value) {
        return value.contains(keyword);
    }

    @NonNull
    @Override
    public String errorMessage() {
        return String.format(Locale.getDefault(), "Value does not contain '%s'", keyword);
    }
}
