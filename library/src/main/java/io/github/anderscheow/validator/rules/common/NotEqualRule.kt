package io.github.anderscheow.validator.rules.common

import android.support.annotation.StringRes
import io.github.anderscheow.validator.rules.BaseRule
import java.util.*

class NotEqualRule<T> : BaseRule<T> {

    private var keyword: T? = null

    constructor(keyword: T) : super(String.format(Locale.getDefault(), "Value equal to '%s'", keyword)) {
        this.keyword = keyword
    }

    constructor(keyword: T, @StringRes errorRes: Int) : super(errorRes) {
        this.keyword = keyword
    }

    constructor(keyword: T, errorMessage: String) : super(errorMessage) {
        this.keyword = keyword
    }

    override fun validate(value: T?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value != keyword
        }
    }
}
