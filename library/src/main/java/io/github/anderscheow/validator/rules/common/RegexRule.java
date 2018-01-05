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
    public boolean validate(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

        if (value instanceof String) {
            return ((String) value).matches(regex);
        }

        throw new ClassCastException("Required String value");
    }
}
