package io.github.anderscheow.validator.conditions.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.conditions.Condition
import io.github.anderscheow.validator.rules.Rule

class And : Condition {

    constructor(rules: List<Rule>) : super(rules, "Does not match 'And' condition")

    constructor(rules: List<Rule>, @StringRes errorRes: Int) : super(rules, errorRes)

    constructor(rules: List<Rule>, errorMessage: String) : super(rules, errorMessage)

    constructor(rule: Rule) : super(listOf(rule), "Does not match 'And' condition")

    constructor(rule: Rule, @StringRes errorRes: Int) : super(listOf(rule), errorRes)

    constructor(rule: Rule, errorMessage: String) : super(listOf(rule), errorMessage)

    override fun validate(value: String?): Boolean {
        for (baseRule in rules) {
            if (baseRule.validate(value).not()) {
                return false
            }
        }
        return true
    }
}

fun Validation.matchAllRules(rules: List<Rule>): Validation {
    conditions.add(And(rules))
    return this
}

fun Validation.matchAllRules(rules: List<Rule>, @StringRes errorRes: Int): Validation {
    conditions.add(And(rules, errorRes))
    return this
}

fun Validation.matchAllRules(rules: List<Rule>, errorMessage: String): Validation {
    conditions.add(And(rules, errorMessage))
    return this
}