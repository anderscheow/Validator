package io.github.anderscheow.validator.rules.regex;

import android.support.annotation.NonNull;

import io.github.anderscheow.validator.rules.common.RegexRule;

public class EmailRule extends RegexRule {

    private static final String EMAIL_REGEX = "\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\\b";

    public EmailRule() {
        super(EMAIL_REGEX);
    }

    @NonNull
    @Override
    public String errorMessage() {
        return "Value does not match email regex";
    }
}
