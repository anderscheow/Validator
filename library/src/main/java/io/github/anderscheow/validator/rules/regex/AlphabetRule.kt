package io.github.anderscheow.validator.rules.regex

import androidx.annotation.StringRes

import io.github.anderscheow.validator.rules.common.RegexRule

class AlphabetRule : RegexRule {

    constructor() : super(ALPHABET_REGEX, "Value does not match alphabet regex")

    constructor(@StringRes errorRes: Int) : super(ALPHABET_REGEX, errorRes)

    constructor(errorMessage: String) : super(ALPHABET_REGEX, errorMessage)

    companion object {

        private const val ALPHABET_REGEX = "^[a-zA-Z]*$"
    }
}
