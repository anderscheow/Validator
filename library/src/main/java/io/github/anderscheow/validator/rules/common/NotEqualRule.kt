package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.Rule

class NotEqualRule : Rule {

    private var keyword: Any

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

fun notEqualTo(keyword: String, @StringRes errorRes: Int): NotEqualRule =
    NotEqualRule(keyword, errorRes)

fun notEqualTo(keyword: String, errorMessage: String): NotEqualRule =
    NotEqualRule(keyword, errorMessage)