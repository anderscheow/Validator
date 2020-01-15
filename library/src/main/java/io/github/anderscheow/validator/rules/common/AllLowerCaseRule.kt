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

    constructor(locale: Locale = Locale.getDefault(), @StringRes errorRes: Int)
            : super(errorRes) {
        this.locale = locale
    }

    constructor(locale: Locale = Locale.getDefault(), errorMessage: String)
            : super(errorMessage) {
        this.locale = locale
    }

    override fun validate(value: Any?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value.toString().toLowerCase(locale) == value.toString()
        }
    }
}
