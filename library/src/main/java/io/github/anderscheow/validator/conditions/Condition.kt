package io.github.anderscheow.validator.conditions

import android.support.annotation.StringRes
import io.github.anderscheow.validator.rules.BaseRule
import io.github.anderscheow.validator.util.ErrorMessage
import io.github.anderscheow.validator.util.Validate
import java.util.*

abstract class Condition : Validate<Any>, ErrorMessage {

    @StringRes
    private var errorRes: Int = -1
    private var errorString: String = "Invalid input"

    val baseRules: MutableList<BaseRule<Any>> = ArrayList()

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

    fun add(baseRule: BaseRule<Any>): Condition {
        baseRules.add(baseRule)
        return this
    }
}
