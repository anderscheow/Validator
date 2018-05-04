package io.github.anderscheow.validator.util

import android.support.annotation.StringRes

interface ErrorMessage {

    val isErrorAvailable: Boolean
        get() = isErrorResAvailable || isErrorMessageAvailable

    val isErrorResAvailable: Boolean
        get() = getErrorRes() != -1

    val isErrorMessageAvailable: Boolean
        get() = !getErrorMessage().isEmpty()

    @StringRes
    fun getErrorRes(): Int

    fun getErrorMessage(): String
}
