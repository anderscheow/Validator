package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.rules.BaseRule
import java.util.*

class EqualRule : BaseRule {

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
    baseRules.add(EqualRule(keyword))
    return this
}

fun Validation.equalTo(keyword: String, @StringRes errorRes: Int): Validation {
    baseRules.add(EqualRule(keyword, errorRes))
    return this
}

fun Validation.equalTo(keyword: String, errorMessage: String): Validation {
    baseRules.add(EqualRule(keyword, errorMessage))
    return this
}