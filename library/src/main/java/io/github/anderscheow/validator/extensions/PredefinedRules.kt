@file:Suppress("UNUSED")

package io.github.anderscheow.validator.extensions

import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.conditions.Condition
import io.github.anderscheow.validator.conditions.common.And
import io.github.anderscheow.validator.conditions.common.Or
import io.github.anderscheow.validator.rules.BaseRule
import io.github.anderscheow.validator.rules.common.*
import io.github.anderscheow.validator.rules.regex.*
import java.util.*

/**
 *  Extension for Validation
 */
fun Validation.contain(keyword: String, ignoreCase: Boolean = false): Validation {
    baseRules.add(ContainRule(keyword, ignoreCase))
    return this
}

fun Validation.notContain(keyword: String, ignoreCase: Boolean = false): Validation {
    baseRules.add(NotContainRule(keyword, ignoreCase))
    return this
}

fun Validation.equalTo(keyword: String): Validation {
    baseRules.add(EqualRule(keyword))
    return this
}

fun Validation.notEqualTo(keyword: String): Validation {
    baseRules.add(NotEqualRule(keyword))
    return this
}

fun Validation.withinRange(minLength: Int, maxLength: Int): Validation {
    baseRules.add(LengthRule(minLength, maxLength))
    return this
}

fun Validation.minimumLength(minLength: Int): Validation {
    baseRules.add(MinRule(minLength))
    return this
}

fun Validation.maximumLength(maxLength: Int): Validation {
    baseRules.add(MaxRule(maxLength))
    return this
}

fun Validation.email(): Validation {
    baseRules.add(EmailRule())
    return this
}

fun Validation.alphanumericOnly(): Validation {
    baseRules.add(AlphanumericRule())
    return this
}

fun Validation.alphabetOnly(): Validation {
    baseRules.add(AlphabetRule())
    return this
}

fun Validation.digitsOnly(): Validation {
    baseRules.add(DigitsRule())
    return this
}

fun Validation.symbolsOnly(): Validation {
    baseRules.add(SymbolRule())
    return this
}

fun Validation.allUppercase(locale: Locale = Locale.getDefault()): Validation {
    baseRules.add(AllUpperCaseRule(locale))
    return this
}

fun Validation.allLowercase(locale: Locale = Locale.getDefault()): Validation {
    baseRules.add(AllLowerCaseRule(locale))
    return this
}

fun Validation.startsWith(keyword: String, ignoreCase: Boolean = false): Validation {
    baseRules.add(StartsWithRule(keyword, ignoreCase))
    return this
}

fun Validation.endsWith(keyword: String, ignoreCase: Boolean = false): Validation {
    baseRules.add(EndsWithRule(keyword, ignoreCase))
    return this
}

fun Validation.withCreditCard(regex: CreditCardRule.CreditCardRegex): Validation {
    baseRules.add(CreditCardRule(regex))
    return this
}

fun Validation.withPassword(regex: PasswordRule.PasswordRegex): Validation {
    baseRules.add(PasswordRule(regex))
    return this
}

fun Validation.matchAtLeastOneRule(baseRules: Array<BaseRule>): Validation {
    conditions.add(Or().addAll(baseRules.toList()))
    return this
}

fun Validation.matchAllRules(baseRules: Array<BaseRule>): Validation {
    conditions.add(And().addAll(baseRules.toList()))
    return this
}

/**
 *  Extension for Condition
 */
fun Condition.contain(keyword: String, ignoreCase: Boolean = false): Condition {
    baseRules.add(ContainRule(keyword, ignoreCase))
    return this
}

fun Condition.notContain(keyword: String, ignoreCase: Boolean = false): Condition {
    baseRules.add(NotContainRule(keyword, ignoreCase))
    return this
}

fun Condition.equalTo(keyword: String): Condition {
    baseRules.add(EqualRule(keyword))
    return this
}

fun Condition.notEqualTo(keyword: String): Condition {
    baseRules.add(NotEqualRule(keyword))
    return this
}

fun Condition.withinRange(minLength: Int, maxLength: Int): Condition {
    baseRules.add(LengthRule(minLength, maxLength))
    return this
}

fun Condition.minimumLength(minLength: Int): Condition {
    baseRules.add(MinRule(minLength))
    return this
}

fun Condition.maximumLength(maxLength: Int): Condition {
    baseRules.add(MaxRule(maxLength))
    return this
}

fun Condition.email(): Condition {
    baseRules.add(EmailRule())
    return this
}

fun Condition.alphanumericOnly(): Condition {
    baseRules.add(AlphanumericRule())
    return this
}

fun Condition.alphabetOnly(): Condition {
    baseRules.add(AlphabetRule())
    return this
}

fun Condition.digitsOnly(): Condition {
    baseRules.add(DigitsRule())
    return this
}

fun Condition.symbolsOnly(): Condition {
    baseRules.add(SymbolRule())
    return this
}

fun Condition.allUppercase(locale: Locale = Locale.getDefault()): Condition {
    baseRules.add(AllUpperCaseRule(locale))
    return this
}

fun Condition.allLowercase(locale: Locale = Locale.getDefault()): Condition {
    baseRules.add(AllLowerCaseRule(locale))
    return this
}

fun Condition.startsWith(keyword: String, ignoreCase: Boolean = false): Condition {
    baseRules.add(StartsWithRule(keyword, ignoreCase))
    return this
}

fun Condition.endsWith(keyword: String, ignoreCase: Boolean = false): Condition {
    baseRules.add(EndsWithRule(keyword, ignoreCase))
    return this
}

fun Condition.withCreditCard(regex: CreditCardRule.CreditCardRegex): Condition {
    baseRules.add(CreditCardRule(regex))
    return this
}

fun Condition.withPassword(regex: PasswordRule.PasswordRegex): Condition {
    baseRules.add(PasswordRule(regex))
    return this
}