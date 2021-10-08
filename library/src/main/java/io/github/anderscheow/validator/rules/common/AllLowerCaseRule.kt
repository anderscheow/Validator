package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation

import io.github.anderscheow.validator.rules.Rule
import java.util.*

class AllLowerCaseRule : Rule {

    private var locale: Locale

    constructor(locale: Locale = Locale.getDefault())
            : super("Value is not all lowercase") {
        this.locale = locale
    }

    constructor(@StringRes errorRes: Int, locale: Locale = Locale.getDefault())
            : super(errorRes) {
        this.locale = locale
    }

    constructor(errorMessage: String, locale: Locale = Locale.getDefault())
            : super(errorMessage) {
        this.locale = locale
    }

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value.lowercase(locale) == value
        }
    }
}

fun Validation.allLowercase(locale: Locale = Locale.getDefault()): Validation {
    baseRules.add(AllLowerCaseRule(locale))
    return this
}

fun Validation.allLowercase(@StringRes errorRes: Int, locale: Locale = Locale.getDefault()): Validation {
    baseRules.add(AllLowerCaseRule(errorRes, locale))
    return this
}

fun Validation.allLowercase(errorMessage: String, locale: Locale = Locale.getDefault()): Validation {
    baseRules.add(AllLowerCaseRule(errorMessage, locale))
    return this
}