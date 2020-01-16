package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.BaseRule
import java.util.*

class MinRule : BaseRule {

    private var minLength: Int = 0

    constructor(minLength: Int) :
            super(String.format(Locale.getDefault(), "Length must exceed at least %d characters", minLength)) {
        this.minLength = minLength
    }

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
