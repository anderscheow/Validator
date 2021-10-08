package io.github.anderscheow.validator.rules.regex

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.rules.common.RegexRule

class SymbolRule : RegexRule {

    constructor() : super(ALPHABET_REGEX, "Value does not match symbol regex")

    constructor(@StringRes errorRes: Int) : super(ALPHABET_REGEX, errorRes)

    constructor(errorMessage: String) : super(ALPHABET_REGEX, errorMessage)

    companion object {

        private const val ALPHABET_REGEX = "^[-!@#\$%^&*()_+|~=`{}\\[\\]:\";'<>?,.\\/]*\$"
    }
}

fun Validation.symbolsOnly(): Validation {
    rules.add(SymbolRule())
    return this
}

fun Validation.symbolsOnly(@StringRes errorRes: Int): Validation {
    rules.add(SymbolRule(errorRes))
    return this
}

fun Validation.symbolsOnly(errorMessage: String): Validation {
    rules.add(SymbolRule(errorMessage))
    return this
}