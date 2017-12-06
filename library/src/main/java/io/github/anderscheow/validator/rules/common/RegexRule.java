package io.github.anderscheow.validator.rules.common;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.github.anderscheow.validator.rules.BaseRule;

public abstract class RegexRule extends BaseRule {

    private String regex;

    public RegexRule(@NonNull String regex) {
        super("Does not match regex rule");
        this.regex = regex;
    }

    public RegexRule(@NonNull String regex, @StringRes int errorRes) {
        super(errorRes);
        this.regex = regex;
    }

    public RegexRule(@NonNull String regex, @NonNull String errorMessage) {
        super(errorMessage);
        this.regex = regex;
    }

    @Override
    public boolean validate(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        return value.matches(regex);
    }
}
