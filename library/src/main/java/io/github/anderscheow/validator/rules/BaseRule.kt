package io.github.anderscheow.validator.rules

import android.support.annotation.StringRes
import io.github.anderscheow.validator.util.ErrorMessage
import io.github.anderscheow.validator.util.Validate

abstract class BaseRule<T> : Validate<T>, ErrorMessage {

    @StringRes
    private var errorRes: Int = -1
    private var errorString: String = "Invalid input"

    constructor()

    constructor(@StringRes errorRes: Int) {
        this.errorRes = errorRes
    }

    constructor(errorString: String) {
        this.errorString = errorString
    }

    override fun getErrorRes(): Int {
        return errorRes
    }

    override fun getErrorMessage(): String {
        return errorString
    }
}
