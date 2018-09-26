package io.github.anderscheow.validator.rules.regex

import androidx.annotation.StringRes

import io.github.anderscheow.validator.rules.common.RegexRule

class DigitsRule : RegexRule {

    constructor() : super(DIGITS_REGEX, "Value does not match digits regex")

    constructor(@StringRes errorRes: Int) : super(DIGITS_REGEX, errorRes)

    constructor(errorMessage: String) : super(DIGITS_REGEX, errorMessage)

    companion object {

        private const val DIGITS_REGEX = "\\d+"
    }
}
