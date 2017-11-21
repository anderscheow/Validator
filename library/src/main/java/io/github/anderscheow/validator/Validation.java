package io.github.anderscheow.validator;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import io.github.anderscheow.validator.conditions.Condition;
import io.github.anderscheow.validator.rules.BaseRule;

public class Validation {

    private TextInputLayout textInputLayout;

    private List<BaseRule> baseRules;

    private List<Condition> conditions;

    public Validation(@NonNull TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;

        this.baseRules = new ArrayList<>();
        this.conditions = new ArrayList<>();
    }

    @Deprecated
    public Validation addRule(@NonNull BaseRule baseRule) {
        baseRules.add(baseRule);
        return this;
    }

    public Validation add(@NonNull BaseRule baseRule) {
        baseRules.add(baseRule);
        return this;
    }

    public Validation add(@NonNull Condition condition) {
        conditions.add(condition);
        return this;
    }

    TextInputLayout getTextInputLayout() {
        return textInputLayout;
    }

    List<BaseRule> getBaseRules() {
        return baseRules;
    }

    List<Condition> getConditions() {
        return conditions;
    }
}
