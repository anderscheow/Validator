package io.github.anderscheow.validator.conditions

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.BaseRule
import io.github.anderscheow.validator.interfaces.ErrorImpl
import io.github.anderscheow.validator.interfaces.Validate
import java.util.*

abstract class Condition : ErrorImpl, Validate {

    val baseRules: MutableList<BaseRule> = ArrayList()

    constructor()

    constructor(@StringRes errorRes: Int) : super(errorRes)

    constructor(errorString: String) : super(errorString)

    fun add(baseRule: BaseRule): Condition {
        baseRules.add(baseRule)
        return this
    }

    fun addAll(baseRules: List<BaseRule>): Condition {
        this.baseRules.addAll(baseRules)
        return this
    }
}
