package io.github.anderscheow.validator.conditions;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.github.anderscheow.validator.rules.BaseRule;
import io.github.anderscheow.validator.util.ErrorMessage;
import io.github.anderscheow.validator.util.Validate;

public abstract class Condition extends ErrorMessage implements Validate {

    private List<BaseRule> baseRules;

    public Condition() {
        this.baseRules = new ArrayList<>();
    }

    public Condition add(@NonNull BaseRule baseRule) {
        baseRules.add(baseRule);
        return this;
    }

    protected List<BaseRule> getBaseRules() {
        return baseRules;
    }
}
