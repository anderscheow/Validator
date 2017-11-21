package io.github.anderscheow.validator.conditions;

import android.support.annotation.NonNull;

import io.github.anderscheow.validator.rules.BaseRule;

public class Or extends Condition {

    @Override
    public boolean validate(String value) {
        for (BaseRule baseRule : getBaseRules()) {
            if (baseRule.validate(value)) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    @Override
    public String errorMessage() {
        return "Does not match 'Or' condition";
    }
}
