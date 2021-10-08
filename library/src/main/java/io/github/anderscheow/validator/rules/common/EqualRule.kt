package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.rules.Rule
import java.util.*

class EqualRule : Rule {

    private var keyword: Any

    constructor(keyword: Any) :
            super(String.format(Locale.getDefault(), "Value does not equal to '%s'", keyword)) {
        this.keyword = keyword
    }

    constructor(keyword: Any, @StringRes errorRes: Int) :
            super(errorRes) {
        this.keyword = keyword
    }

    constructor(keyword: Any, errorMessage: String) :
            super(errorMessage) {
        this.keyword = keyword
    }

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value == keyword
        }
    }
}

fun Validation.equalTo(keyword: String): Validation {
    rules.add(EqualRule(keyword))
    return this
}

fun Validation.equalTo(keyword: String, @StringRes errorRes: Int): Validation {
    rules.add(EqualRule(keyword, errorRes))
    return this
}

fun Validation.equalTo(keyword: String, errorMessage: String): Validation {
    rules.add(EqualRule(keyword, errorMessage))
    return this
}