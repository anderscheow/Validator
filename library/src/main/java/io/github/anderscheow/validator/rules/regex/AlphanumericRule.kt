package io.github.anderscheow.validator.rules.regex

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.common.RegexRule

class AlphanumericRule : RegexRule {

    constructor(@StringRes errorRes: Int) : super(ALPHANUMERIC_REGEX, errorRes)

    constructor(errorMessage: String) : super(ALPHANUMERIC_REGEX, errorMessage)

    companion object {
        private const val ALPHANUMERIC_REGEX = "^[a-zA-Z0-9]*$"
    }
}

fun alphanumericOnly(@StringRes errorRes: Int): AlphanumericRule = AlphanumericRule(errorRes)

fun alphanumericOnly(errorMessage: String): AlphanumericRule = AlphanumericRule(errorMessage)