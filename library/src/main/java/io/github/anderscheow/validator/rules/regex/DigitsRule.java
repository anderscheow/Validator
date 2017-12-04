package io.github.anderscheow.validator.rules.regex;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.github.anderscheow.validator.rules.common.RegexRule;

public class DigitsRule extends RegexRule {

    private static final String DIGITS_REGEX = "\\d+";

    public DigitsRule() {
        super(DIGITS_REGEX, "Value does not match digits regex");
    }

    public DigitsRule(@StringRes int errorRes) {
        super(DIGITS_REGEX, errorRes);
    }

    public DigitsRule(@NonNull String errorMessage) {
        super(DIGITS_REGEX, errorMessage);
    }
}
