package io.github.anderscheow.validator.rules.regex

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.common.RegexRule

class CreditCardRule : RegexRule {

    enum class CreditCardRegex(private val value: String) {
        VISA("^4[0-9]{12}(?:[0-9]{3})?$"),
        MASTERCARD("^5[1-5][0-9]{14}$"),
        AMERICAN_EXPRESS("^3[47][0-9]{13}$"),
        DINERS_CLUB("^3(?:0[0-5]|[68][0-9])[0-9]{11}$"),
        DISCOVER("^6(?:011|5[0-9]{2})[0-9]{12}$"),
        JCB("^(?:2131|1800|35\\d{3})\\d{11}$"),
        CHINA_UNION_PAY("^62[0-9]{14,17}$");

        override fun toString(): String {
            return value
        }
    }

    constructor(regex: CreditCardRegex, @StringRes errorRes: Int) : super(
        regex.toString(),
        errorRes
    )

    constructor(regex: CreditCardRegex, errorMessage: String) : super(
        regex.toString(),
        errorMessage
    )
}

fun withCreditCard(
    regex: CreditCardRule.CreditCardRegex,
    @StringRes errorRes: Int
): CreditCardRule = CreditCardRule(regex, errorRes)

fun withCreditCard(regex: CreditCardRule.CreditCardRegex, errorMessage: String): CreditCardRule =
    CreditCardRule(regex, errorMessage)