package io.github.anderscheow.validator.conditions.common;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.github.anderscheow.validator.conditions.Condition;
import io.github.anderscheow.validator.rules.BaseRule;

public class Or extends Condition {

    public Or() {
        super("Does not match 'Or' condition");
    }

    public Or(@StringRes int errorRes) {
        super(errorRes);
    }

    public Or(@NonNull String errorMessage) {
        super(errorMessage);
    }

    @Override
    public boolean validate(String value) {
        for (BaseRule baseRule : getBaseRules()) {
            if (baseRule.validate(value)) {
                return true;
            }
        }
        return false;
    }
}
