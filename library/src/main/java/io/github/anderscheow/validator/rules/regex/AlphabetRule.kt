package io.github.anderscheow.validator.rules.regex

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation

import io.github.anderscheow.validator.rules.common.RegexRule

class AlphabetRule : RegexRule {

    constructor() : super(ALPHABET_REGEX, "Value does not match alphabet regex")

    constructor(@StringRes errorRes: Int) : super(ALPHABET_REGEX, errorRes)

    constructor(errorMessage: String) : super(ALPHABET_REGEX, errorMessage)

    companion object {

        private const val ALPHABET_REGEX = "^[a-zA-Z]*$"
    }
}

fun Validation.alphabetOnly(): Validation {
    rules.add(AlphabetRule())
    return this
}

fun Validation.alphabetOnly(@StringRes errorRes: Int): Validation {
    rules.add(AlphabetRule(errorRes))
    return this
}

fun Validation.alphabetOnly(errorMessage: String): Validation {
    rules.add(AlphabetRule(errorMessage))
    return this
}