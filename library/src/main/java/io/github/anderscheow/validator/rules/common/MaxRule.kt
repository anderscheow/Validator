package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.Rule

class MaxRule : Rule {

    private var maxLength: Int

    constructor(maxLength: Int, @StringRes errorRes: Int) :
            super(errorRes) {
        this.maxLength = maxLength
    }

    constructor(maxLength: Int, errorMessage: String) :
            super(errorMessage) {
        this.maxLength = maxLength
    }

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value.length <= maxLength
        }
    }
}

fun maximumLength(maxLength: Int, @StringRes errorRes: Int): MaxRule = MaxRule(maxLength, errorRes)

fun maximumLength(maxLength: Int, errorMessage: String): MaxRule = MaxRule(maxLength, errorMessage)
