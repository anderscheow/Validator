package io.github.anderscheow.validator.rules.common;

import io.github.anderscheow.validator.rules.BaseRule;

public abstract class RegexRule extends BaseRule {

    private String regex;

    public RegexRule(String regex) {
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
