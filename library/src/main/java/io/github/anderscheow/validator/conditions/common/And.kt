package io.github.anderscheow.validator.conditions.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.conditions.Condition
import io.github.anderscheow.validator.rules.Rule
import io.github.anderscheow.validator.rules.RuleBuilder

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

class AndBuilder {
    val ruleList = arrayListOf<Rule>()

    fun rules(init: RuleBuilder.() -> Unit) {
        ruleList.addAll(RuleBuilder().apply(init).ruleList)
    }

    operator fun Rule.unaryPlus() {
        ruleList.add(this)
    }
}

fun and(
    @StringRes errorRes: Int? = null,
    errorMessage: String? = null,
    init: AndBuilder.() -> Unit
): And {
    val and = AndBuilder()
    and.init()
    return when {
        errorRes != null -> {
            And(and.ruleList, errorRes)
        }
        errorMessage != null -> {
            And(and.ruleList, errorMessage)
        }
        else -> throw IllegalStateException("errorRes or errorMessage cannot be null")
    }
}