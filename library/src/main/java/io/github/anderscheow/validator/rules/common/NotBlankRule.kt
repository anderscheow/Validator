package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation

import io.github.anderscheow.validator.rules.Rule

class NotBlankRule : Rule {

    constructor() : super("Value must not be empty")

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

fun Validation.notBlank(): Validation {
    baseRules.add(NotBlankRule())
    return this
}

fun Validation.notBlank(@StringRes errorRes: Int): Validation {
    baseRules.add(NotBlankRule(errorRes))
    return this
}

fun Validation.notBlank(errorMessage: String): Validation {
    baseRules.add(NotBlankRule(errorMessage))
    return this
}
