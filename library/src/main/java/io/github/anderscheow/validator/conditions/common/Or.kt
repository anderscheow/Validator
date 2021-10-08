package io.github.anderscheow.validator.conditions.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.conditions.Condition
import io.github.anderscheow.validator.rules.Rule

class Or : Condition {

    constructor() : super("Does not match 'Or' condition")

    constructor(@StringRes errorRes: Int) : super(errorRes)

    constructor(errorMessage: String) : super(errorMessage)

    override fun validate(value: String?): Boolean {
        for (baseRule in rules) {
            if (baseRule.validate(value)) {
                return true
            }
        }
        return false
    }
}

fun Validation.matchAtLeastOneRule(rules: Array<Rule>): Validation {
    conditions.add(Or().addAll(rules.toList()))
    return this
}

fun Validation.matchAtLeastOneRule(rules: Array<Rule>, @StringRes errorRes: Int): Validation {
    conditions.add(Or(errorRes).addAll(rules.toList()))
    return this
}

fun Validation.matchAtLeastOneRule(rules: Array<Rule>, errorMessage: String): Validation {
    conditions.add(Or(errorMessage).addAll(rules.toList()))
    return this
}