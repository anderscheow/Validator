package io.github.anderscheow.validator.rules.common

import android.support.annotation.StringRes
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

    override fun validate(value: Any?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            var parsedDate: Date? = null

            when (value) {
                is String -> try {
                    parsedDate = dateFormat.parse(value)
                } catch (ignored: ParseException) {
                }

                is Date -> parsedDate = value

                else -> throw ClassCastException("Required String or Date value")
            }

            return parsedDate != null && parsedDate.after(Date())
        }
    }
}
