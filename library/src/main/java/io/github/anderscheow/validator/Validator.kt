package io.github.anderscheow.validator

import android.content.Context
import io.github.anderscheow.validator.constant.Mode
import io.github.anderscheow.validator.interfaces.ErrorImpl

class Validator private constructor(private val context: Context) {

    private var mode = Mode.CONTINUOUS
    private var listener: OnValidateListener? = null
    private var validations = mutableListOf<Validation>()

    interface OnValidateListener {
        @Throws(IndexOutOfBoundsException::class)
        fun onValidateSuccess(values: List<String>)

        fun onValidateFailed(errors: List<String>)
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
        val errors = ArrayList<String>()

        // Clear all validations
        this.validations.clear()

        // Add all validations
        this.validations.addAll(validations)

        // Clear all errors before proceed
        clear()

        // Iterate each validation
        for (validation in validations) {
            val editText = validation.textInputLayout.editText

            val value = editText?.text?.toString()
            if (value != null) {
                val isCurrentValueValid = validate(value, validation, errors)

                if (isCurrentValueValid) {
                    isValid = true
                    values.add(value)
                } else {
                    isOverallValid = false
                    isValid = false

                    if (mode == Mode.SINGLE) {
                        break
                    }
                }
            }
        }

        // Verify result
        if (isValid && isOverallValid) {
            listener?.onValidateSuccess(values)
        } else {
            listener?.onValidateFailed(errors)
        }
    }

    fun clear() {
        clearAllErrors()
    }

    private fun clearAllErrors() {
        for (validation in validations) {
            validation.textInputLayout.error = null
            validation.textInputLayout.isErrorEnabled = false
        }
    }

    // Iterate each type of rules
    private fun validate(
        value: String,
        validation: Validation,
        errors: ArrayList<String>
    ): Boolean {
        var isCurrentValueValid = validateBaseRules(value, validation, errors)
        if (isCurrentValueValid) {
            isCurrentValueValid = validateConditions(value, validation, errors)
        }

        return isCurrentValueValid
    }

    private fun validateBaseRules(
        value: String,
        validation: Validation,
        errors: ArrayList<String>
    ): Boolean {
        for (baseRule in validation.rules) {
            if (baseRule.validate(value).not()) {
                showErrorMessage(validation, baseRule, errors)

                return false
            }
        }

        return true
    }

    private fun validateConditions(
        value: String,
        validation: Validation,
        errors: ArrayList<String>
    ): Boolean {
        for (condition in validation.conditions) {
            if (condition.validate(value).not()) {
                showErrorMessage(validation, condition, errors)

                return false
            }
        }

        return true
    }

    private fun showErrorMessage(
        validation: Validation,
        errorMessage: ErrorImpl,
        errors: ArrayList<String>
    ) {
        validation.setError(context, errorMessage, errors)
    }

    companion object {
        fun with(context: Context): Validator {
            return Validator(context)
        }
    }
}

class ValidatorBuilder {
    var mode = Mode.CONTINUOUS
    lateinit var listener: Validator.OnValidateListener
    lateinit var validations: Array<Validation>

    fun validate(vararg validations: Validation) {
        this.validations = arrayOf(*validations)
    }
}

fun validator(context: Context, init: ValidatorBuilder.() -> Unit): Validator {
    val validator = ValidatorBuilder()
    validator.init()
    return Validator.with(context).apply {
        this.setMode(validator.mode)
        this.setListener(validator.listener)
        this.validate(*validator.validations)
    }
}
