package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation

import io.github.anderscheow.validator.rules.Rule

class NotNullRule : Rule {

    constructor() : super("Value must not be null")

    constructor(@StringRes errorRes: Int) : super(errorRes)

    constructor(errorMessage: String) : super(errorMessage)

    override fun validate(value: String?): Boolean {
        return value != null
    }
}

fun Validation.notNull(): Validation {
    rules.add(NotNullRule())
    return this
}

fun Validation.notNull(@StringRes errorRes: Int): Validation {
    rules.add(NotNullRule(errorRes))
    return this
}

fun Validation.notNull(errorMessage: String): Validation {
    rules.add(NotNullRule(errorMessage))
    return this
}