package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.rules.BaseRule
import java.text.DateFormat
import java.text.ParseException
import java.util.*

class FutureRule : BaseRule {

    private var dateFormat: DateFormat

    constructor(dateFormat: DateFormat) :
            super("Does not match future rule") {
        this.dateFormat = dateFormat
    }

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

fun Validation.future(dateFormat: DateFormat): Validation {
    baseRules.add(FutureRule(dateFormat))
    return this
}

fun Validation.future(dateFormat: DateFormat, @StringRes errorRes: Int): Validation {
    baseRules.add(FutureRule(dateFormat, errorRes))
    return this
}

fun Validation.future(dateFormat: DateFormat, errorMessage: String): Validation {
    baseRules.add(FutureRule(dateFormat, errorMessage))
    return this
}