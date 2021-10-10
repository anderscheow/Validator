package io.github.anderscheow.validator.rules.regex

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.common.RegexRule

class AlphabetRule : RegexRule {

    constructor(@StringRes errorRes: Int) : super(ALPHABET_REGEX, errorRes)

    constructor(errorMessage: String) : super(ALPHABET_REGEX, errorMessage)

    companion object {
        private const val ALPHABET_REGEX = "^[a-zA-Z]*$"
    }
}

fun alphabetOnly(@StringRes errorRes: Int): AlphabetRule = AlphabetRule(errorRes)

fun alphabetOnly(errorMessage: String): AlphabetRule = AlphabetRule(errorMessage)