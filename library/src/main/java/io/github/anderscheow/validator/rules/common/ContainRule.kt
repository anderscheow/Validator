package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.rules.Rule
import java.util.*

class ContainRule : Rule {

    private var keyword: String

    private var ignoreCase = false

    constructor(keyword: String, ignoreCase: Boolean = false) :
            super(String.format(Locale.getDefault(), "Value does not contain '%s'", keyword)) {
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
            return value.contains(keyword, ignoreCase)
        }
    }
}

fun Validation.contain(keyword: String, ignoreCase: Boolean = false): Validation {
    rules.add(ContainRule(keyword, ignoreCase))
    return this
}

fun Validation.contain(keyword: String, @StringRes errorRes: Int, ignoreCase: Boolean = false): Validation {
    rules.add(ContainRule(keyword, errorRes, ignoreCase))
    return this
}

fun Validation.contain(keyword: String, errorMessage: String, ignoreCase: Boolean = false): Validation {
    rules.add(ContainRule(keyword, errorMessage, ignoreCase))
    return this
}