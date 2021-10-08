package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.rules.Rule
import java.util.*

class MaxRule : Rule {

    private var maxLength: Int = 0

    constructor(maxLength: Int) :
            super(String.format(Locale.getDefault(), "Length must not exceed %d characters", maxLength)) {
        this.maxLength = maxLength
    }

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

fun Validation.maximumLength(maxLength: Int): Validation {
    rules.add(MaxRule(maxLength))
    return this
}

fun Validation.maximumLength(maxLength: Int, @StringRes errorRes: Int): Validation {
    rules.add(MaxRule(maxLength, errorRes))
    return this
}

fun Validation.maximumLength(maxLength: Int, errorMessage: String): Validation {
    rules.add(MaxRule(maxLength, errorMessage))
    return this
}
