package io.github.anderscheow.validator;

import android.support.design.widget.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import io.github.anderscheow.validator.rules.BaseRule;

public class Validation {

    private TextInputLayout textInputLayout;

    private List<BaseRule> baseRules;

    public Validation(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;

        this.baseRules = new ArrayList<>();
    }

    public TextInputLayout getTextInputLayout() {
        return textInputLayout;
    }

    public Validation addRule(BaseRule baseRule) {
        baseRules.add(baseRule);
        return this;
    }

    public List<BaseRule> getBaseRules() {
        return baseRules;
    }
}
