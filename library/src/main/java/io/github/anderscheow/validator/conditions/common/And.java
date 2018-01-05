package io.github.anderscheow.validator.conditions.common;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import io.github.anderscheow.validator.conditions.Condition;
import io.github.anderscheow.validator.rules.BaseRule;

public class And extends Condition {

    public And() {
        super("Does not match 'And' condition");
    }

    public And(@StringRes int errorRes) {
        super(errorRes);
    }

    public And(@NonNull String errorMessage) {
        super(errorMessage);
    }

    @Override
    public boolean validate(Object value) {
        for (BaseRule baseRule : getBaseRules()) {
            if (!baseRule.validate(value)) {
                return false;
            }
        }
        return true;
    }
}
