package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.Rule

class NotNullRule : Rule {

    constructor(@StringRes errorRes: Int) : super(errorRes)

    constructor(errorMessage: String) : super(errorMessage)

    override fun validate(value: String?): Boolean {
        return value != null
    }
}

fun notNull(@StringRes errorRes: Int): NotNullRule = NotNullRule(errorRes)

fun notNull(errorMessage: String): NotNullRule = NotNullRule(errorMessage)