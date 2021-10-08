package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.rules.Rule
import java.util.*

class NotEqualRule : Rule {

    private var keyword: Any

    constructor(keyword: Any) : super(String.format(Locale.getDefault(), "Value equal to '%s'", keyword)) {
        this.keyword = keyword
    }

    constructor(keyword: Any, @StringRes errorRes: Int) : super(errorRes) {
        this.keyword = keyword
    }

    constructor(keyword: Any, errorMessage: String) : super(errorMessage) {
        this.keyword = keyword
    }

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value != keyword
        }
    }
}

fun Validation.notEqualTo(keyword: String): Validation {
    rules.add(NotEqualRule(keyword))
    return this
}

fun Validation.notEqualTo(keyword: String, @StringRes errorRes: Int): Validation {
    rules.add(NotEqualRule(keyword, errorRes))
    return this
}

fun Validation.notEqualTo(keyword: String, errorMessage: String): Validation {
    rules.add(NotEqualRule(keyword, errorMessage))
    return this
}