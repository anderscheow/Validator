package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.rules.Rule
import java.text.DateFormat
import java.text.ParseException
import java.util.*

class PastRule : Rule {

    private var dateFormat: DateFormat

    constructor(dateFormat: DateFormat) : super("Does not match past rule") {
        this.dateFormat = dateFormat
    }

    constructor(dateFormat: DateFormat, @StringRes errorRes: Int) : super(errorRes) {
        this.dateFormat = dateFormat
    }

    constructor(dateFormat: DateFormat, errorMessage: String) : super(errorMessage) {
        this.dateFormat = dateFormat
    }

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            var parsedDate: Date? = null

            try {
                parsedDate = dateFormat.parse(value)
            } catch (ignored: ParseException) {
            }

            return parsedDate != null && parsedDate.before(Date())
        }
    }
}

fun Validation.past(dateFormat: DateFormat): Validation {
    rules.add(PastRule(dateFormat))
    return this
}

fun Validation.past(dateFormat: DateFormat, @StringRes errorRes: Int): Validation {
    rules.add(PastRule(dateFormat, errorRes))
    return this
}

fun Validation.past(dateFormat: DateFormat, errorMessage: String): Validation {
    rules.add(PastRule(dateFormat, errorMessage))
    return this
}