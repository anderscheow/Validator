package io.github.anderscheow.validator.util

interface Validate<T> {

    fun validate(value: T?): Boolean
}
