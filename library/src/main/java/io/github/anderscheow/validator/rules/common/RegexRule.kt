package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.Rule

open class RegexRule : Rule {

    private var regex: String

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

fun regex(regex: String, @StringRes errorRes: Int): RegexRule = RegexRule(regex, errorRes)

fun regex(regex: String, errorMessage: String): RegexRule = RegexRule(regex, errorMessage)