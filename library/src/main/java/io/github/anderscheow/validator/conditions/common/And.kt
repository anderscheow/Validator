package io.github.anderscheow.validator.conditions.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.conditions.Condition
import io.github.anderscheow.validator.conditions.ConditionBuilder
import io.github.anderscheow.validator.conditions.ConditionsBuilder
import io.github.anderscheow.validator.rules.Rule

class And : Condition {

    constructor(rules: List<Rule>, @StringRes errorRes: Int) : super(rules, errorRes)

    constructor(rules: List<Rule>, errorMessage: String) : super(rules, errorMessage)

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

class AndBuilder : ConditionBuilder() {
}

fun ConditionsBuilder.and(
    @StringRes errorRes: Int,
    init: AndBuilder.() -> Unit
): And {
    val and = AndBuilder()
    and.init()
    return And(and.ruleList, errorRes)
}

fun ConditionsBuilder.and(
    errorMessage: String,
    init: AndBuilder.() -> Unit
): And {
    val and = AndBuilder()
    and.init()
    return And(and.ruleList, errorMessage)
}