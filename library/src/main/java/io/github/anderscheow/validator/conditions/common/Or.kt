package io.github.anderscheow.validator.conditions.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.conditions.Condition
import io.github.anderscheow.validator.rules.BaseRule

class Or : Condition {

    constructor() : super("Does not match 'Or' condition")

    constructor(@StringRes errorRes: Int) : super(errorRes)

    constructor(errorMessage: String) : super(errorMessage)

    override fun validate(value: String?): Boolean {
        for (baseRule in baseRules) {
            if (baseRule.validate(value)) {
                return true
            }
        }
        return false
    }
}

fun Validation.matchAtLeastOneRule(baseRules: Array<BaseRule>): Validation {
    conditions.add(Or().addAll(baseRules.toList()))
    return this
}

fun Validation.matchAtLeastOneRule(baseRules: Array<BaseRule>, @StringRes errorRes: Int): Validation {
    conditions.add(Or(errorRes).addAll(baseRules.toList()))
    return this
}

fun Validation.matchAtLeastOneRule(baseRules: Array<BaseRule>, errorMessage: String): Validation {
    conditions.add(Or(errorMessage).addAll(baseRules.toList()))
    return this
}