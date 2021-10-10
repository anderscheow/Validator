package io.github.anderscheow.validator.rules.regex

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.common.RegexRule

class EmailRule : RegexRule {

    constructor(@StringRes errorRes: Int) : super(EMAIL_REGEX, errorRes)

    constructor(errorMessage: String) : super(EMAIL_REGEX, errorMessage)

    companion object {
        private const val EMAIL_REGEX = "\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\\b"
    }
}

fun email(@StringRes errorRes: Int): EmailRule = EmailRule(errorRes)

fun email(errorMessage: String): EmailRule = EmailRule(errorMessage)