package io.github.anderscheow.validator.rules.regex;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.github.anderscheow.validator.rules.common.RegexRule;

public class PasswordRule extends RegexRule {

    enum PasswordRegex {
        ANY(".+"),
        ALPHA("\\w+"),
        ALPHA_MIXED_CASE("(?=.*[a-z])(?=.*[A-Z]).+"),
        NUMERIC("\\d+"),
        ALPHA_NUMERIC("(?=.*[a-zA-Z])(?=.*[\\d]).+"),
        ALPHA_NUMERIC_SYMBOLS("(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*([^\\w])).+");

        private final String name;

        PasswordRegex(String s) {
            name = s;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public PasswordRule(@NonNull PasswordRegex regex) {
        super(regex.name, "Value does not match any password regex");
    }

    public PasswordRule(@NonNull PasswordRegex regex, @StringRes int errorRes) {
        super(regex.name, errorRes);
    }

    public PasswordRule(@NonNull PasswordRegex regex, @NonNull String errorMessage) {
        super(regex.name, errorMessage);
    }
}
