package io.github.anderscheow.validator.conditions

import androidx.annotation.StringRes
import io.github.anderscheow.validator.interfaces.ErrorImpl
import io.github.anderscheow.validator.interfaces.Validate
import io.github.anderscheow.validator.rules.Rule
import io.github.anderscheow.validator.rules.RulesBuilder

abstract class Condition : ErrorImpl, Validate {

    val rules: List<Rule>

    constructor(rules: List<Rule>, @StringRes errorRes: Int) : super(errorRes) {
        this.rules = rules
    }

    constructor(rules: List<Rule>, errorString: String) : super(errorString) {
        this.rules = rules
    }

    constructor(rule: Rule, @StringRes errorRes: Int) : super(errorRes) {
        this.rules = listOf(rule)
    }

    constructor(rule: Rule, errorString: String) : super(errorString) {
        this.rules = listOf(rule)
    }
}

class ConditionsBuilder {
    val conditionList = arrayListOf<Condition>()

    operator fun Condition.unaryPlus() {
        conditionList.add(this)
    }
}

open class ConditionBuilder {
    val ruleList = arrayListOf<Rule>()

    fun rules(init: RulesBuilder.() -> Unit) {
        ruleList.addAll(RulesBuilder().apply(init).ruleList)
    }

    operator fun Rule.unaryPlus() {
        ruleList.add(this)
    }

    operator fun List<Rule>.unaryPlus() {
        ruleList.addAll(this)
    }
}