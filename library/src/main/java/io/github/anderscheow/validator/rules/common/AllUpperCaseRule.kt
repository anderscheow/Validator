package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.Rule
import java.util.*

class AllUpperCaseRule : Rule {

    private var locale: Locale

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

fun allUppercase(
    @StringRes errorRes: Int,
    locale: Locale = Locale.getDefault()
): AllUpperCaseRule = AllUpperCaseRule(errorRes, locale)

fun allUppercase(
    errorMessage: String,
    locale: Locale = Locale.getDefault()
): AllUpperCaseRule = AllUpperCaseRule(errorMessage, locale)