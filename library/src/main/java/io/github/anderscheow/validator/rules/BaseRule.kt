package io.github.anderscheow.validator.rules

import androidx.annotation.StringRes
import io.github.anderscheow.validator.interfaces.ErrorImpl
import io.github.anderscheow.validator.interfaces.Validate

abstract class BaseRule : ErrorImpl, Validate {

    constructor()

    constructor(@StringRes errorRes: Int) : super(errorRes)

    constructor(errorString: String) : super(errorString)
}
