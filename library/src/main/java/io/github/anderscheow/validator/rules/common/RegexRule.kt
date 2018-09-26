package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes

import io.github.anderscheow.validator.rules.BaseRule

open class RegexRule : BaseRule {

    private var regex: String

    constructor(regex: String) : super("Does not match regex rule") {
        this.regex = regex
    }

    constructor(regex: String, @StringRes errorRes: Int) : super(errorRes) {
        this.regex = regex
    }

    constructor(regex: String, errorMessage: String) : super(errorMessage) {
        this.regex = regex
    }

    override fun validate(value: Any?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value.toString().matches(regex.toRegex())
        }
    }
}
