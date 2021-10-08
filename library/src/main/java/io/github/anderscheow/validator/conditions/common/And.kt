package io.github.anderscheow.validator.conditions.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.conditions.Condition
import io.github.anderscheow.validator.rules.Rule

class And : Condition {

    constructor() : super("Does not match 'And' condition")

    constructor(@StringRes errorRes: Int) : super(errorRes)

    constructor(errorMessage: String) : super(errorMessage)

    override fun validate(value: String?): Boolean {
        for (baseRule in rules) {
            if (!baseRule.validate(value)) {
                return false
            }
        }
        return true
    }
}

fun Validation.matchAllRules(rules: Array<Rule>): Validation {
    conditions.add(And().addAll(rules.toList()))
    return this
}

fun Validation.matchAllRules(rules: Array<Rule>, @StringRes errorRes: Int): Validation {
    conditions.add(And(errorRes).addAll(rules.toList()))
    return this
}

fun Validation.matchAllRules(rules: Array<Rule>, errorMessage: String): Validation {
    conditions.add(And(errorMessage).addAll(rules.toList()))
    return this
}