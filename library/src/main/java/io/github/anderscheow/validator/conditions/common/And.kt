package io.github.anderscheow.validator.conditions.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.conditions.Condition

class And : Condition {

    constructor() : super("Does not match 'And' condition")

    constructor(@StringRes errorRes: Int) : super(errorRes)

    constructor(errorMessage: String) : super(errorMessage)

    override fun validate(value: String?): Boolean {
        for (baseRule in baseRules) {
            if (!baseRule.validate(value)) {
                return false
            }
        }
        return true
    }
}
