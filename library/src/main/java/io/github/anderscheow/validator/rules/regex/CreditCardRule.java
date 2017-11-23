package io.github.anderscheow.validator.rules.regex;

import android.support.annotation.NonNull;

import io.github.anderscheow.validator.rules.common.RegexRule;

public class CreditCardRule extends RegexRule {

    enum CreditCardRegex {
        VISA("^4[0-9]{12}(?:[0-9]{3})?$"),
        MASTERCARD("^5[1-5][0-9]{14}$"),
        AMERICAN_EXPRESS("^3[47][0-9]{13}$"),
        DINERS_CLUB("^3(?:0[0-5]|[68][0-9])[0-9]{11}$"),
        DISCOVER("^6(?:011|5[0-9]{2})[0-9]{12}$"),
        JCB("^(?:2131|1800|35\\d{3})\\d{11}$"),
        CHINA_UNION_PAY("^62[0-9]{14,17}$");

        private final String name;

        CreditCardRegex(String s) {
            name = s;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public CreditCardRule(CreditCardRegex regex) {
        super(regex.name);
    }

    @NonNull
    @Override
    public String errorMessage() {
        return "Value does not match any credit card regex";
    }
}
