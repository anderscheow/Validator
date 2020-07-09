package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.rules.BaseRule
import java.util.*

class StartsWithRule : BaseRule {

    private var keyword: String

    private var ignoreCase = false

    constructor(keyword: String, ignoreCase: Boolean = false) :
            super(String.format(Locale.getDefault(), "Value does not start with '%s'", keyword)) {
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
            return value.startsWith(keyword, ignoreCase)
        }
    }
}

fun Validation.startsWith(keyword: String, ignoreCase: Boolean = false): Validation {
    baseRules.add(StartsWithRule(keyword, ignoreCase))
    return this
}

fun Validation.startsWith(keyword: String, @StringRes errorRes: Int, ignoreCase: Boolean = false): Validation {
    baseRules.add(StartsWithRule(keyword, errorRes, ignoreCase))
    return this
}

fun Validation.startsWith(keyword: String, errorMessage: String, ignoreCase: Boolean = false): Validation {
    baseRules.add(StartsWithRule(keyword, errorMessage, ignoreCase))
    return this
}