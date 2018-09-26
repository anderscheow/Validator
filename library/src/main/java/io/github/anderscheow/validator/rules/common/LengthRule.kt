package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.BaseRule
import java.util.*

class LengthRule : BaseRule {

    private var minLength: Int = 0
    private var maxLength: Int = 0

    constructor(minLength: Int, maxLength: Int) :
            super(String.format(Locale.getDefault(), "Length must be between %d and %d", minLength, maxLength)) {
        this.minLength = minLength
        this.maxLength = maxLength
    }

    constructor(minLength: Int, maxLength: Int, @StringRes errorRes: Int) :
            super(errorRes) {
        this.minLength = minLength
        this.maxLength = maxLength
    }

    constructor(minLength: Int, maxLength: Int, errorMessage: String) :
            super(errorMessage) {
        this.minLength = minLength
        this.maxLength = maxLength
    }

    override fun validate(value: Any?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            value.toString().let {
                assertMinMax(minLength, maxLength)

                val length = it.length

                var isMinValid = true
                if (minLength != Integer.MIN_VALUE) {
                    isMinValid = length >= minLength
                }

                var isMaxValid = true
                if (maxLength != Integer.MAX_VALUE) {
                    isMaxValid = length <= maxLength
                }

                return isMinValid && isMaxValid
            }
        }
    }

    private fun assertMinMax(min: Int, max: Int) {
        if (min > max) {
            val message = String.format(Locale.getDefault(), "'minLength' (%d) " + "should be smaller than 'maxLength' (%d)", min, max)
            throw IllegalStateException(message)
        }
    }
}
