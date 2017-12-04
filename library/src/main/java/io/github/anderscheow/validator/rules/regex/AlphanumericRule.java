package io.github.anderscheow.validator.rules.regex;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.github.anderscheow.validator.rules.common.RegexRule;

public class AlphanumericRule extends RegexRule {

    private static final String ALPHANUMERIC_REGEX = "(?=.*[a-zA-Z])(?=.*[\\d]).+";

    public AlphanumericRule() {
        super(ALPHANUMERIC_REGEX, "Value does not match alphanumeric regex");
    }

    public AlphanumericRule(@StringRes int errorRes) {
        super(ALPHANUMERIC_REGEX, errorRes);
    }

    public AlphanumericRule(@NonNull String errorMessage) {
        super(ALPHANUMERIC_REGEX, errorMessage);
    }
}
