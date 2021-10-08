package io.github.anderscheow.validator.conditions.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.conditions.Condition
import io.github.anderscheow.validator.rules.Rule

class Or : Condition {

    constructor(rules: List<Rule>) : super(rules, "Does not match 'Or' condition")

    constructor(rules: List<Rule>, @StringRes errorRes: Int) : super(rules, errorRes)

    constructor(rules: List<Rule>, errorMessage: String) : super(rules, errorMessage)

    constructor(rule: Rule) : super(listOf(rule), "Does not match 'Or' condition")

    constructor(rule: Rule, @StringRes errorRes: Int) : super(listOf(rule), errorRes)

    constructor(rule: Rule, errorMessage: String) : super(listOf(rule), errorMessage)

    override fun validate(value: String?): Boolean {
        for (baseRule in rules) {
            if (baseRule.validate(value)) {
                return true
            }
        }
        return false
    }
}

fun Validation.matchAtLeastOneRule(rules: List<Rule>): Validation {
    conditions.add(Or(rules))
    return this
}

fun Validation.matchAtLeastOneRule(rules: List<Rule>, @StringRes errorRes: Int): Validation {
    conditions.add(Or(rules, errorRes))
    return this
}

fun Validation.matchAtLeastOneRule(rules: List<Rule>, errorMessage: String): Validation {
    conditions.add(Or(rules, errorMessage))
    return this
}