@file:Suppress("UNUSED")

package io.github.anderscheow.validator

import android.content.Context
import com.google.android.material.textfield.TextInputLayout
import io.github.anderscheow.validator.conditions.Condition
import io.github.anderscheow.validator.rules.BaseRule
import io.github.anderscheow.validator.util.ErrorMessage
import java.util.*

class Validation {

    var textInputLayout: TextInputLayout? = null
    var textInput: Any? = null

    val baseRules: MutableList<BaseRule> = ArrayList()
    val conditions: MutableList<Condition> = ArrayList()


    constructor(textInputLayout: TextInputLayout) {
        this.textInputLayout = textInputLayout
    }

    constructor(textInput: Any) {
        this.textInput = textInput
    }

    fun add(baseRule: BaseRule): Validation {
        baseRules.add(baseRule)
        return this
    }

    fun add(condition: Condition): Validation {
        conditions.add(condition)
        return this
    }

    fun setError(context: Context, errorMessage: ErrorMessage, errors: ArrayList<String>) {
        if (errorMessage.isErrorAvailable) {
            textInputLayout?.isErrorEnabled = true

            if (errorMessage.isErrorResAvailable) {
                val error = context.getString(errorMessage.getErrorRes())
                errors.add(error)
                textInputLayout?.error = error
            } else if (errorMessage.isErrorMessageAvailable) {
                val error = errorMessage.getErrorMessage()
                errors.add(error)
                textInputLayout?.error = error
            }
        } else {
            throw IllegalStateException("Please either use errorRes or errorMessage as your error output")
        }
    }
}
