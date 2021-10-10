package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.Rule

class NotBlankRule : Rule {

    constructor(@StringRes errorRes: Int) : super(errorRes)

    constructor(errorMessage: String) : super(errorMessage)

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value.isNotBlank()
        }
    }
}

fun notBlank(@StringRes errorRes: Int): NotBlankRule = NotBlankRule(errorRes)

fun notBlank(errorMessage: String): NotBlankRule = NotBlankRule(errorMessage)
