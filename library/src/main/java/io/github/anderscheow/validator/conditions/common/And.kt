package io.github.anderscheow.validator.conditions.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.conditions.Condition
import io.github.anderscheow.validator.rules.BaseRule

class And : Condition {

    constructor() : super("Does not match 'And' condition")

    constructor(@StringRes errorRes: Int) : super(errorRes)

    constructor(errorMessage: String) : super(errorMessage)

    override fun validate(value: String?): Boolean {
        for (baseRule in baseRules) {
            if (!baseRule.validate(value)) {
                return false
            }
        }
        return true
    }
}

fun Validation.matchAllRules(baseRules: Array<BaseRule>): Validation {
    conditions.add(And().addAll(baseRules.toList()))
    return this
}

fun Validation.matchAllRules(baseRules: Array<BaseRule>, @StringRes errorRes: Int): Validation {
    conditions.add(And(errorRes).addAll(baseRules.toList()))
    return this
}

fun Validation.matchAllRules(baseRules: Array<BaseRule>, errorMessage: String): Validation {
    conditions.add(And(errorMessage).addAll(baseRules.toList()))
    return this
}