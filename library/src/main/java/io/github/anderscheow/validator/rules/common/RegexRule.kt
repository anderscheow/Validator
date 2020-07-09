package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation

import io.github.anderscheow.validator.rules.BaseRule

open class RegexRule : BaseRule {

    private var regex: String

    constructor(regex: String) : super("Does not match regex rule") {
        this.regex = regex
    }

    constructor(regex: String, @StringRes errorRes: Int) : super(errorRes) {
        this.regex = regex
    }

    constructor(regex: String, errorMessage: String) : super(errorMessage) {
        this.regex = regex
    }

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value.matches(regex.toRegex())
        }
    }
}

fun Validation.regex(regex: String): Validation {
    baseRules.add(RegexRule(regex))
    return this
}

fun Validation.regex(regex: String, @StringRes errorRes: Int): Validation {
    baseRules.add(RegexRule(regex, errorRes))
    return this
}

fun Validation.regex(regex: String, errorMessage: String): Validation {
    baseRules.add(RegexRule(regex, errorMessage))
    return this
}