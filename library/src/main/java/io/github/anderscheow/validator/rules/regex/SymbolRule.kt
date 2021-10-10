package io.github.anderscheow.validator.rules.regex

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.common.RegexRule

class SymbolRule : RegexRule {

    constructor(@StringRes errorRes: Int) : super(ALPHABET_REGEX, errorRes)

    constructor(errorMessage: String) : super(ALPHABET_REGEX, errorMessage)

    companion object {
        private const val ALPHABET_REGEX = "^[-!@#\$%^&*()_+|~=`{}\\[\\]:\";'<>?,.\\/]*\$"
    }
}

fun symbolsOnly(@StringRes errorRes: Int): SymbolRule = SymbolRule(errorRes)

fun symbolsOnly(errorMessage: String): SymbolRule = SymbolRule(errorMessage)