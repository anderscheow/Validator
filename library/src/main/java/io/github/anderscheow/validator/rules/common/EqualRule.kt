package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.Rule

class EqualRule : Rule {

    private var keyword: Any

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

fun equalTo(keyword: String, @StringRes errorRes: Int): EqualRule = EqualRule(keyword, errorRes)

fun equalTo(keyword: String, errorMessage: String): EqualRule = EqualRule(keyword, errorMessage)