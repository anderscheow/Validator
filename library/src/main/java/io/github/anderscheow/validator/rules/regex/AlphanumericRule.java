package io.github.anderscheow.validator.rules.regex;

import android.support.annotation.NonNull;

import io.github.anderscheow.validator.rules.common.RegexRule;

public class AlphanumericRule extends RegexRule {

    private static final String ALPHANUMERIC_REGEX = "(?=.*[a-zA-Z])(?=.*[\\d]).+";

    public AlphanumericRule() {
        super(ALPHANUMERIC_REGEX);
    }

    @NonNull
    @Override
    public String errorMessage() {
        return "Value does not match alphanumeric regex";
    }
}
