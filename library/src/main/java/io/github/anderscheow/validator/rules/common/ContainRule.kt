package io.github.anderscheow.validator.rules.common

import android.support.annotation.StringRes
import io.github.anderscheow.validator.rules.BaseRule
import java.util.*

class ContainRule : BaseRule<Any> {

    private var keyword: String

    private var ignoreCase = false

    constructor(keyword: String, ignoreCase: Boolean = false) :
            super(String.format(Locale.getDefault(), "Value does not contain '%s'", keyword)) {
        this.keyword = keyword
        this.ignoreCase = ignoreCase
    }

    constructor(keyword: String, @StringRes errorRes: Int, ignoreCase: Boolean = false) :
            super(errorRes) {
        this.keyword = keyword
        this.ignoreCase = ignoreCase
    }

    constructor(keyword: String, errorMessage: String, ignoreCase: Boolean = false) :
            super(errorMessage) {
        this.keyword = keyword
        this.ignoreCase = ignoreCase
    }

    override fun validate(value: Any?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value.toString().contains(keyword, ignoreCase)
        }
    }
}
