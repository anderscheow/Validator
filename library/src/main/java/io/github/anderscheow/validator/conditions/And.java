package io.github.anderscheow.validator.conditions;

import android.support.annotation.NonNull;

import io.github.anderscheow.validator.rules.BaseRule;

public class And extends Condition {

    @Override
    public boolean validate(String value) {
        boolean isValid = false;

        for (BaseRule baseRule : getBaseRules()) {
            if (baseRule.validate(value)) {
                isValid = true;
            }
        }
        return isValid;
    }

    @NonNull
    @Override
    public String errorMessage() {
        return "Does not match 'And' condition";
    }
}
