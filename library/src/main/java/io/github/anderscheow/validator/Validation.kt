@file:Suppress("UNUSED")

package io.github.anderscheow.validator

import android.content.Context
import com.google.android.material.textfield.TextInputLayout
import io.github.anderscheow.validator.conditions.Condition
import io.github.anderscheow.validator.rules.Rule
import io.github.anderscheow.validator.interfaces.ErrorImpl
import java.util.*

class Validation(val textInputLayout: TextInputLayout) {

    val baseRules = arrayListOf<Rule>()
    val conditions = arrayListOf<Condition>()

    fun add(rule: Rule): Validation {
        baseRules.add(rule)
        return this
    }

    fun add(condition: Condition): Validation {
        conditions.add(condition)
        return this
    }

    fun setError(context: Context, errorImpl: ErrorImpl, errors: ArrayList<String>) {
        if (errorImpl.isErrorAvailable) {
            textInputLayout.isErrorEnabled = true

            if (errorImpl.isErrorResAvailable) {
                val error = context.getString(errorImpl.errorRes)
                errors.add(error)
                textInputLayout.error = error
            } else if (errorImpl.isErrorMessageAvailable) {
                val error = errorImpl.errorString
                errors.add(error)
                textInputLayout.error = error
            }
        } else {
            throw IllegalStateException("Please either use errorRes or errorMessage as your error output")
        }
    }
}