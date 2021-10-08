package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation

import io.github.anderscheow.validator.rules.Rule

open class RegexRule : Rule {

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
    rules.add(RegexRule(regex))
    return this
}

fun Validation.regex(regex: String, @StringRes errorRes: Int): Validation {
    rules.add(RegexRule(regex, errorRes))
    return this
}

fun Validation.regex(regex: String, errorMessage: String): Validation {
    rules.add(RegexRule(regex, errorMessage))
    return this
}