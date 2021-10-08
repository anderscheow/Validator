package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.rules.Rule
import java.util.*

class EndsWithRule : Rule {

    private var keyword: String

    private var ignoreCase = false

    constructor(keyword: String, ignoreCase: Boolean = false) :
            super(String.format(Locale.getDefault(), "Value does not end with '%s'", keyword)) {
        this.keyword = keyword
        this.ignoreCase = ignoreCase
    }

    constructor(keyword: String, @StringRes errorRes: Int, ignoreCase: Boolean = false) :
            super(errorRes) {
        this.keyword = keyword
        this.ignoreCase = ignoreCase
    }

    constructor(keyword: String, errorMessage: String, ignoreCase: Boolean = false) :
            super(errorMessage) {
        this.keyword = keyword
        this.ignoreCase = ignoreCase
    }

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value.endsWith(keyword, ignoreCase)
        }
    }
}

fun Validation.endsWith(keyword: String, ignoreCase: Boolean = false): Validation {
    baseRules.add(EndsWithRule(keyword, ignoreCase))
    return this
}

fun Validation.endsWith(keyword: String, @StringRes errorRes: Int, ignoreCase: Boolean = false): Validation {
    baseRules.add(EndsWithRule(keyword, errorRes, ignoreCase))
    return this
}

fun Validation.endsWith(keyword: String, errorMessage: String, ignoreCase: Boolean = false): Validation {
    baseRules.add(EndsWithRule(keyword, errorMessage, ignoreCase))
    return this
}