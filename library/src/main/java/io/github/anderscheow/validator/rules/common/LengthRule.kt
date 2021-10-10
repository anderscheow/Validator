package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.Rule
import java.util.*

class LengthRule : Rule {

    private var minLength: Int
    private var maxLength: Int

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

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            value.let {
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
            val message = String.format(
                Locale.getDefault(),
                "'minLength' (%d) " + "should be smaller than 'maxLength' (%d)",
                min,
                max
            )
            throw IllegalStateException(message)
        }
    }
}

fun withinRange(minLength: Int, maxLength: Int, @StringRes errorRes: Int): LengthRule =
    LengthRule(minLength, maxLength, errorRes)

fun withinRange(minLength: Int, maxLength: Int, errorMessage: String): LengthRule =
    LengthRule(minLength, maxLength, errorMessage)
