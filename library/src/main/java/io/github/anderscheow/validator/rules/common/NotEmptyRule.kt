package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes

import io.github.anderscheow.validator.rules.BaseRule

class NotEmptyRule : BaseRule {

    constructor() : super("Value must not be empty")

    constructor(@StringRes errorRes: Int) : super(errorRes)

    constructor(errorMessage: String) : super(errorMessage)

    override fun validate(value: Any?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value.toString().isNotEmpty()
        }
    }
}
