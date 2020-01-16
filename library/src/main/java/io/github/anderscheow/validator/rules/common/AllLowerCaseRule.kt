package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes

import io.github.anderscheow.validator.rules.BaseRule
import java.util.*

class AllLowerCaseRule : BaseRule {

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
            return value.toLowerCase(locale) == value
        }
    }
}
