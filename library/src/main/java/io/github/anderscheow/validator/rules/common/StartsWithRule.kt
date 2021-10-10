package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.Rule

class StartsWithRule : Rule {

    private var keyword: String

    private var ignoreCase = false

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

fun startsWith(
    keyword: String,
    @StringRes errorRes: Int,
    ignoreCase: Boolean = false
): StartsWithRule = StartsWithRule(keyword, errorRes, ignoreCase)

fun startsWith(
    keyword: String,
    errorMessage: String,
    ignoreCase: Boolean = false
): StartsWithRule = StartsWithRule(keyword, errorMessage, ignoreCase)