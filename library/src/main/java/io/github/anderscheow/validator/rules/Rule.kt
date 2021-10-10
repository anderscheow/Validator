package io.github.anderscheow.validator.rules

import androidx.annotation.StringRes
import io.github.anderscheow.validator.interfaces.ErrorImpl
import io.github.anderscheow.validator.interfaces.Validate

abstract class Rule : ErrorImpl, Validate {

    constructor(@StringRes errorRes: Int) : super(errorRes)

    constructor(errorString: String) : super(errorString)
}

class RulesBuilder {
    val ruleList = arrayListOf<Rule>()

    operator fun Rule.unaryPlus() {
        ruleList.add(this)
    }
}
