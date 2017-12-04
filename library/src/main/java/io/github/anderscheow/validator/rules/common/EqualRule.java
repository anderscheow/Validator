package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import java.util.Locale;

import io.github.anderscheow.validator.rules.BaseRule;

public class EqualRule extends BaseRule {

    private String keyword;

    public EqualRule(String keyword) {
        super(String.format(Locale.getDefault(), "Value does not equal to '%s'", keyword));
        this.keyword = keyword;
    }

    public EqualRule(String keyword, @StringRes int errorRes) {
        super(errorRes);
        this.keyword = keyword;
    }

    public EqualRule(String keyword, @NonNull String errorMessage) {
        super(errorMessage);
        this.keyword = keyword;
    }

    @Override
    public boolean validate(String value) {
        return value.equals(keyword);
    }
}
