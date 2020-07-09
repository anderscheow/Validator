package io.github.anderscheow.validator.rules.regex

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.rules.common.RegexRule

class PasswordRule : RegexRule {

    enum class PasswordRegex constructor(private val value: String) {
        ANY(".+"),
        ALPHA("\\w+"),
        ALPHA_MIXED_CASE("(?=.*[a-z])(?=.*[A-Z]).+"),
        NUMERIC("\\d+"),
        ALPHA_NUMERIC("(?=.*[a-zA-Z])(?=.*[\\d]).+"),
        ALPHA_NUMERIC_SYMBOLS("(?=.*[a-zA-Z])(?=.*[\\d])(?=.*([^\\w])).+");

        override fun toString(): String {
            return value
        }
    }

    constructor(regex: PasswordRegex) : super(regex.toString(), "Value does not match any password regex")

    constructor(regex: PasswordRegex, @StringRes errorRes: Int) : super(regex.toString(), errorRes)

    constructor(regex: PasswordRegex, errorMessage: String) : super(regex.toString(), errorMessage)
}

fun Validation.withPassword(regex: PasswordRule.PasswordRegex): Validation {
    baseRules.add(PasswordRule(regex))
    return this
}

fun Validation.withPassword(regex: PasswordRule.PasswordRegex, @StringRes errorRes: Int): Validation {
    baseRules.add(PasswordRule(regex, errorRes))
    return this
}

fun Validation.withPassword(regex: PasswordRule.PasswordRegex, errorMessage: String): Validation {
    baseRules.add(PasswordRule(regex, errorMessage))
    return this
}