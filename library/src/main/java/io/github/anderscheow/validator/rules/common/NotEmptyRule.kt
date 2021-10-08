package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation

import io.github.anderscheow.validator.rules.Rule

class NotEmptyRule : Rule {

    constructor() : super("Value must not be empty")

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

fun Validation.notEmpty(): Validation {
    rules.add(NotEmptyRule())
    return this
}

fun Validation.notEmpty(@StringRes errorRes: Int): Validation {
    rules.add(NotEmptyRule(errorRes))
    return this
}

fun Validation.notEmpty(errorMessage: String): Validation {
    rules.add(NotEmptyRule(errorMessage))
    return this
}
