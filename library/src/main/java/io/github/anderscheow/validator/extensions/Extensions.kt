package io.github.anderscheow.validator.extensions

import com.google.android.material.textfield.TextInputLayout
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.conditions.Condition
import io.github.anderscheow.validator.rules.BaseRule

fun TextInputLayout.validate(): Validation {
    return Validation(this)
}

fun TextInputLayout.add(baseRule: BaseRule): Validation {
    return Validation(this)
            .add(baseRule)
}

fun TextInputLayout.add(condition: Condition): Validation {
    return Validation(this)
            .add(condition)
}