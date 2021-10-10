package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.Rule

class NotEmptyRule : Rule {

    constructor(@StringRes errorRes: Int) : super(errorRes)

    constructor(errorMessage: String) : super(errorMessage)

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value.isNotEmpty()
        }
    }
}

fun notEmpty(@StringRes errorRes: Int): NotEmptyRule = NotEmptyRule(errorRes)

fun notEmpty(errorMessage: String): NotEmptyRule = NotEmptyRule(errorMessage)
