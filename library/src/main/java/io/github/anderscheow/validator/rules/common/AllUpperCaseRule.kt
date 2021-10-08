package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.rules.Rule
import java.util.*

class AllUpperCaseRule : Rule {

    private var locale: Locale

    constructor(locale: Locale = Locale.getDefault())
            : super("Value is not all uppercase") {
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
            return value.uppercase(locale) == value
        }
    }
}

fun Validation.allUppercase(locale: Locale = Locale.getDefault()): Validation {
    rules.add(AllUpperCaseRule(locale))
    return this
}

fun Validation.allUppercase(@StringRes errorRes: Int, locale: Locale = Locale.getDefault()): Validation {
    rules.add(AllUpperCaseRule(errorRes, locale))
    return this
}

fun Validation.allUppercase(errorMessage: String, locale: Locale = Locale.getDefault()): Validation {
    rules.add(AllUpperCaseRule(errorMessage, locale))
    return this
}