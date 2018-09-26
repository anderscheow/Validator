package io.github.anderscheow.validator

import android.content.Context
import io.github.anderscheow.validator.constant.Mode
import io.github.anderscheow.validator.util.ErrorMessage

class Validator private constructor(private val context: Context) {

    private var mode = Mode.CONTINUOUS

    private var listener: OnValidateListener? = null

    private var validations = ArrayList<Validation>()

    interface OnValidateListener {
        @Throws(IndexOutOfBoundsException::class)
        fun onValidateSuccess(values: List<String>)

        fun onValidateFailed()
    }

    fun setMode(mode: Mode): Validator {
        this.mode = mode
        return this
    }

    fun setListener(listener: OnValidateListener): Validator {
        this.listener = listener
        return this
    }

    fun validate(vararg validations: Validation) {
        var isOverallValid = true
        var isValid = false
        val values = ArrayList<String>()

        // Clear all validations
        this.validations.clear()

        // Add all validations
        this.validations.addAll(validations)

        // Clear all errors before proceed
        clear()

        // Iterate each validation
        for (validation in validations) {
            val editText = validation.textInputLayout?.editText

            val value = (editText?.text ?: validation.textInput).toString()
            val isCurrentValueValid = validate(value, validation)

            if (isCurrentValueValid) {
                isValid = true
                values.add(value)
            } else {
                isOverallValid = false
                isValid = false

                if (mode == Mode.SINGLE) {
                    return
                }
            }
        }

        // Verify result
        if (isValid && isOverallValid) {
            listener?.onValidateSuccess(values)
        } else {
            listener?.onValidateFailed()
        }
    }

    fun clear() {
        clearAllErrors()
    }

    private fun clearAllErrors() {
        for (validation in validations) {
            validation.textInputLayout?.error = null
            validation.textInputLayout?.isErrorEnabled = false
        }
    }

    // Iterate each type of rules
    private fun validate(value: String, validation: Validation): Boolean {
        var isCurrentValueValid = validateBaseRules(value, validation)
        if (isCurrentValueValid) {
            isCurrentValueValid = validateConditions(value, validation)
        }

        return isCurrentValueValid
    }

    private fun validateBaseRules(value: String, validation: Validation): Boolean {
        for (baseRule in validation.baseRules) {
            if (!baseRule.validate(value)) {
                showErrorMessage(validation, baseRule)

                return false
            }
        }

        return true
    }

    private fun validateConditions(value: String, validation: Validation): Boolean {
        for (condition in validation.conditions) {
            if (!condition.validate(value)) {
                showErrorMessage(validation, condition)

                return false
            }
        }

        return true
    }

    private fun showErrorMessage(validation: Validation, errorMessage: ErrorMessage) {
        validation.setError(context, errorMessage)
    }

    companion object {

        fun with(context: Context): Validator {
            return Validator(context)
        }
    }
}
