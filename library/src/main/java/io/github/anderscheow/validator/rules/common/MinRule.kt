package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.Rule

class MinRule : Rule {

    private var minLength: Int

    constructor(minLength: Int, @StringRes errorRes: Int) :
            super(errorRes) {
        this.minLength = minLength
    }

    constructor(minLength: Int, errorMessage: String) :
            super(errorMessage) {
        this.minLength = minLength
    }

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value.length >= minLength
        }
    }
}

fun minimumLength(minLength: Int, @StringRes errorRes: Int): MinRule = MinRule(minLength, errorRes)

fun minimumLength(minLength: Int, errorMessage: String): MinRule = MinRule(minLength, errorMessage)