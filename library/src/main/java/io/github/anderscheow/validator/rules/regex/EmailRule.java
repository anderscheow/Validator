package io.github.anderscheow.validator.rules.regex;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.github.anderscheow.validator.rules.common.RegexRule;

public class EmailRule extends RegexRule {

    private static final String EMAIL_REGEX = "\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\\b";

    public EmailRule() {
        super(EMAIL_REGEX);
    }

    public EmailRule(@StringRes int errorRes) {
        super(EMAIL_REGEX, errorRes);
    }

    public EmailRule(@NonNull String errorMessage) {
        super(EMAIL_REGEX, errorMessage);
    }

    @NonNull
    @Override
    public String errorMessage() {
        return "Value does not match email regex";
    }
}
