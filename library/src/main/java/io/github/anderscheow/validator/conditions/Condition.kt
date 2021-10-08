package io.github.anderscheow.validator.conditions

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.Rule
import io.github.anderscheow.validator.interfaces.ErrorImpl
import io.github.anderscheow.validator.interfaces.Validate
import java.util.*

abstract class Condition : ErrorImpl, Validate {

    val rules: List<Rule>

    constructor(rules: List<Rule>) {
        this.rules = rules
    }

    constructor(rules: List<Rule>, @StringRes errorRes: Int) : super(errorRes) {
        this.rules = rules
    }

    constructor(rules: List<Rule>, errorString: String) : super(errorString) {
        this.rules = rules
    }

    constructor(rule: Rule) {
        this.rules = listOf(rule)
    }

    constructor(rule: Rule, @StringRes errorRes: Int) : super(errorRes) {
        this.rules = listOf(rule)
    }

    constructor(rule: Rule, errorString: String) : super(errorString) {
        this.rules = listOf(rule)
    }
}
