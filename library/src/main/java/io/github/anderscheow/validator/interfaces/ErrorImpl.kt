package io.github.anderscheow.validator.interfaces

import androidx.annotation.StringRes

abstract class ErrorImpl {

    @StringRes
    var errorRes: Int = -1
        private set

    var errorString: String = "Invalid input"
        private set

    val isErrorAvailable: Boolean
        get() = isErrorResAvailable || isErrorMessageAvailable

    val isErrorResAvailable: Boolean
        get() = errorRes != -1

    val isErrorMessageAvailable: Boolean
        get() = errorString.isNotBlank()

    constructor(errorRes: Int) {
        this.errorRes = errorRes
    }

    constructor(errorString: String) {
        this.errorString = errorString
    }
}
