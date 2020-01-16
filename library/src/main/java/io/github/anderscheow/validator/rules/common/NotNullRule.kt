package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes

import io.github.anderscheow.validator.rules.BaseRule

class NotNullRule : BaseRule {

    constructor() : super("Value must not be null")

    constructor(@StringRes errorRes: Int) : super(errorRes)

    constructor(errorMessage: String) : super(errorMessage)

    override fun validate(value: String?): Boolean {
        return value != null
    }
}
