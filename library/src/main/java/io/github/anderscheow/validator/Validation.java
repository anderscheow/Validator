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
    private List<BaseRule> andRules;
    private List<BaseRule> orRules;

    private List<Condition> conditions;

    public Validation(@NonNull TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;

        this.baseRules = new ArrayList<>();
        this.andRules = new ArrayList<>();
        this.orRules = new ArrayList<>();
        this.conditions = new ArrayList<>();
    }

    /**
     * @deprecated Use {@link #and(BaseRule),
     *                  {@link #or(BaseRule) or
     *                  {@link #add(Condition)}}} )} instead.
     */
    @Deprecated
    public Validation addRule(@NonNull BaseRule baseRule) {
        baseRules.add(baseRule);
        return this;
    }

    public Validation and(@NonNull BaseRule baseRule) {
        andRules.add(baseRule);
        return this;
    }

    public Validation or(@NonNull BaseRule baseRule) {
        orRules.add(baseRule);
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

    List<BaseRule> getAndRules() {
        return andRules;
    }

    List<BaseRule> getOrRules() {
        return orRules;
    }

    List<Condition> getConditions() {
        return conditions;
    }
}
