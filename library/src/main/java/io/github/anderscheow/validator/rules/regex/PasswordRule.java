package io.github.anderscheow.validator.rules.regex;

import android.support.annotation.NonNull;

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

    public PasswordRule(PasswordRegex regex) {
        super(regex.name);
    }

    @NonNull
    @Override
    public String errorMessage() {
        return "Value does not match any password regex";
    }
}
