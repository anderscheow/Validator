package io.github.anderscheow.validator.rules.regex;

import android.support.annotation.NonNull;

import io.github.anderscheow.validator.rules.common.RegexRule;

public class DigitsRule extends RegexRule {

    private static final String DIGITS_REGEX = "\\d+";

    public DigitsRule() {
        super(DIGITS_REGEX);
    }

    @NonNull
    @Override
    public String errorMessage() {
        return "Value does not match digits regex";
    }
}
