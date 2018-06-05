@file:Suppress("UNUSED")

package io.github.anderscheow.validator

import android.support.design.widget.TextInputLayout
import io.github.anderscheow.validator.conditions.Condition
import io.github.anderscheow.validator.rules.BaseRule
import java.util.*

class Validation {

    var textInputLayout: TextInputLayout? = null
    var textInput: Any? = null

    val baseRules: MutableList<BaseRule> = ArrayList()
    val conditions: MutableList<Condition> = ArrayList()

    constructor(textInputLayout: TextInputLayout) {
        this.textInputLayout = textInputLayout
    }

    constructor(textInput: Any) {
        this.textInput = textInput
    }

    fun add(baseRule: BaseRule): Validation {
        baseRules.add(baseRule)
        return this
    }

    fun add(condition: Condition): Validation {
        conditions.add(condition)
        return this
    }
}
