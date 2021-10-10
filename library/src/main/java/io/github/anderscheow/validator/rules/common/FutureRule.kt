package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.Rule
import java.text.DateFormat
import java.text.ParseException
import java.util.*

class FutureRule : Rule {

    private var dateFormat: DateFormat

    constructor(dateFormat: DateFormat, @StringRes errorRes: Int) :
            super(errorRes) {
        this.dateFormat = dateFormat
    }

    constructor(dateFormat: DateFormat, errorMessage: String) :
            super(errorMessage) {
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

            return parsedDate != null && parsedDate.after(Date())
        }
    }
}

fun future(dateFormat: DateFormat, @StringRes errorRes: Int): FutureRule =
    FutureRule(dateFormat, errorRes)

fun future(dateFormat: DateFormat, errorMessage: String): FutureRule =
    FutureRule(dateFormat, errorMessage)