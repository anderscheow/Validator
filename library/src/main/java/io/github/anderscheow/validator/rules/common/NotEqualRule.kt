package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.BaseRule
import java.util.*

class NotEqualRule : BaseRule {

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

    override fun validate(value: Any?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value != keyword
        }
    }
}
