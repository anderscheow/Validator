package io.github.anderscheow.validator.rules.regex

import android.support.annotation.StringRes

import io.github.anderscheow.validator.rules.common.RegexRule

class AlphanumericRule : RegexRule {

    constructor() : super(ALPHANUMERIC_REGEX, "Value does not match alphanumeric regex")

    constructor(@StringRes errorRes: Int) : super(ALPHANUMERIC_REGEX, errorRes)

    constructor(errorMessage: String) : super(ALPHANUMERIC_REGEX, errorMessage)

    companion object {

        private const val ALPHANUMERIC_REGEX = "^[a-zA-Z0-9]*$"
    }
}
