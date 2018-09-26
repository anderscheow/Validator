package io.github.anderscheow.validator.conditions

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.BaseRule
import io.github.anderscheow.validator.util.ErrorMessage
import io.github.anderscheow.validator.util.Validate
import java.util.*

abstract class Condition : Validate, ErrorMessage {

    @StringRes
    private var errorRes: Int = -1
    private var errorString: String = "Invalid input"

    val baseRules: MutableList<BaseRule> = ArrayList()

    constructor()

    constructor(@StringRes errorRes: Int) {
        this.errorRes = errorRes
    }

    constructor(errorString: String) {
        this.errorString = errorString
    }

    override fun getErrorRes(): Int {
        return errorRes
    }

    override fun getErrorMessage(): String {
        return errorString
    }

    fun add(baseRule: BaseRule): Condition {
        baseRules.add(baseRule)
        return this
    }

    fun addAll(baseRules: List<BaseRule>): Condition {
        this.baseRules.addAll(baseRules)
        return this
    }
}
