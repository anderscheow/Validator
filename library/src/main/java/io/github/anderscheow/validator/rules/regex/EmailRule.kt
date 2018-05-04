package io.github.anderscheow.validator.rules.regex

import android.support.annotation.StringRes

import io.github.anderscheow.validator.rules.common.RegexRule

class EmailRule : RegexRule {

    constructor() : super(EMAIL_REGEX, "Value does not match email regex")

    constructor(@StringRes errorRes: Int) : super(EMAIL_REGEX, errorRes)

    constructor(errorMessage: String) : super(EMAIL_REGEX, errorMessage)

    companion object {

        private const val EMAIL_REGEX = "\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\\b"
    }
}
