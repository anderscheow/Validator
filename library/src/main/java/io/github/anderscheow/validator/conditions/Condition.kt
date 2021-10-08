package io.github.anderscheow.validator.conditions

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.Rule
import io.github.anderscheow.validator.interfaces.ErrorImpl
import io.github.anderscheow.validator.interfaces.Validate
import java.util.*

abstract class Condition : ErrorImpl, Validate {

    val rules: MutableList<Rule> = ArrayList()

    constructor()

    constructor(@StringRes errorRes: Int) : super(errorRes)

    constructor(errorString: String) : super(errorString)

    fun add(rule: Rule): Condition {
        rules.add(rule)
        return this
    }

    fun addAll(rules: List<Rule>): Condition {
        this.rules.addAll(rules)
        return this
    }
}
