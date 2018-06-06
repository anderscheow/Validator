package io.github.anderscheow.validator.util

import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.conditions.common.And
import io.github.anderscheow.validator.conditions.common.Or
import io.github.anderscheow.validator.rules.common.*
import io.github.anderscheow.validator.rules.regex.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PredefinedRulesTest {

    @Before
    @Throws(Exception::class)
    fun setUp() {
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }

    /**
     *  Extension for Validation
     */
    @Test
    @Throws(Exception::class)
    fun validate_Validation_Contain() {
        val sample = "contain test"
        val invalidSample = "contain Test"

        val validation = Validation(sample).contain("test")

        assertTrue(validation.baseRules[0].javaClass == ContainRule::class.java)
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_NotContain() {
        val sample = "contain Test"
        val invalidSample = "contain test"

        val validation = Validation(sample).notContain("test")

        assertTrue(validation.baseRules[0].javaClass == NotContainRule::class.java)
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_EqualTo() {
        val sample = "test"
        val invalidSample = "Test"

        val validation = Validation(sample).equalTo("test")

        assertTrue(validation.baseRules[0].javaClass == EqualRule::class.java)
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_NotEqualTo() {
        val sample = "Test"
        val invalidSample = "test"

        val validation = Validation(sample).notEqualTo("test")

        assertTrue(validation.baseRules[0].javaClass == NotEqualRule::class.java)
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_WithinRange() {
        val sample = "Hello World"
        val invalidSample = "Hello"

        val validation = Validation(sample).withinRange(6, 15)

        assertTrue(validation.baseRules[0].javaClass == LengthRule::class.java)
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_MinimumLength() {
        val sample = "Hello World"
        val invalidSample = "Hello"

        val validation = Validation(sample).minimumLength(6)

        assertTrue(validation.baseRules[0].javaClass == MinRule::class.java)
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_MaximumLength() {
        val sample = "Hello"
        val invalidSample = "Hello World"

        val validation = Validation(sample).maximumLength(6)

        assertTrue(validation.baseRules[0].javaClass == MaxRule::class.java)
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_Email() {
        val sample = "hello@world.com"
        val invalidSample = "hello_world.com"

        val validation = Validation(sample).email()

        assertTrue(validation.baseRules[0].javaClass == EmailRule::class.java)
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_AlphanumericOnly() {
        val sample = "helloworld123"
        val invalidSample = "hello world123"

        val validation = Validation(sample).alphanumericOnly()

        assertTrue(validation.baseRules[0].javaClass == AlphanumericRule::class.java)
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_AlphabetOnly() {
        val sample = "helloworld"
        val invalidSample = "hello world123"

        val validation = Validation(sample).alphabetOnly()

        assertTrue(validation.baseRules[0].javaClass == AlphabetRule::class.java)
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_DigitsOnly() {
        val sample = "123"
        val invalidSample = "hello world123"

        val validation = Validation(sample).digitsOnly()

        assertTrue(validation.baseRules[0].javaClass == DigitsRule::class.java)
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_SymbolsOnly() {
        val sample = "!@#"
        val invalidSample = "hello world123"

        val validation = Validation(sample).symbolsOnly()

        assertEquals(validation.baseRules[0].getErrorMessage(), "Value does not contain symbols")
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test(expected = NullPointerException::class)
    @Throws(Exception::class)
    fun validate_Validation_SymbolsOnly_NPE() {
        val sample = "!@#"

        val validation = Validation(sample).symbolsOnly()

        assertFalse(validation.baseRules[0].validate(null))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_AllUppercase() {
        val sample = "HELLO WORLD"
        val invalidSample = "hello world"

        val validation = Validation(sample).allUppercase()

        assertEquals(validation.baseRules[0].getErrorMessage(), "Value is not all uppercase")
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test(expected = NullPointerException::class)
    @Throws(Exception::class)
    fun validate_Validation_AllUppercase_NPE() {
        val sample = "HELLO WORLD"

        val validation = Validation(sample).allUppercase()

        assertFalse(validation.baseRules[0].validate(null))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_AllLowercase() {
        val sample = "hello world"
        val invalidSample = "HELLO WORLD"

        val validation = Validation(sample).allLowercase()

        assertEquals(validation.baseRules[0].getErrorMessage(), "Value is not all lowercase")
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test(expected = NullPointerException::class)
    @Throws(Exception::class)
    fun validate_Validation_AllLowercase_NPE() {
        val sample = "hello world"

        val validation = Validation(sample).allLowercase()

        assertFalse(validation.baseRules[0].validate(null))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_StartsWith() {
        val sample = "hello world"
        val invalidSample = "world hello"

        val validation = Validation(sample).startsWith("hello")

        assertEquals(validation.baseRules[0].getErrorMessage(), "Value is not start with hello")
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test(expected = NullPointerException::class)
    @Throws(Exception::class)
    fun validate_Validation_StartsWith_NPE() {
        val sample = "hello world"

        val validation = Validation(sample).startsWith("hello")

        assertFalse(validation.baseRules[0].validate(null))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_EndsWith() {
        val sample = "hello world"
        val invalidSample = "world hello"

        val validation = Validation(sample).endsWith("world")

        assertEquals(validation.baseRules[0].getErrorMessage(), "Value is not end with world")
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test(expected = NullPointerException::class)
    @Throws(Exception::class)
    fun validate_Validation_EndsWith_NPE() {
        val sample = "hello world"

        val validation = Validation(sample).endsWith("world")

        assertFalse(validation.baseRules[0].validate(null))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_WithCreditCard() {
        val sample = "4111111111111111"
        val invalidSample = "5555555555554444"

        val validation = Validation(sample).withCreditCard(CreditCardRule.CreditCardRegex.VISA)

        assertTrue(validation.baseRules[0].javaClass == CreditCardRule::class.java)
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_WithPassword() {
        val sample = "abc ABC 123 .,"
        val invalidSample = "abc 123 ,./"

        val validation = Validation(sample).withPassword(PasswordRule.PasswordRegex.ALPHA_MIXED_CASE)

        assertTrue(validation.baseRules[0].javaClass == PasswordRule::class.java)
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_MatchAtLeastOneRule() {
        val sample = "hello@world.testing.com"
        val invalidSample = "hello@world_hello@world"

        val validation = Validation(sample).matchAtLeastOneRule(arrayOf(
                EmailRule(),
                MaxRule(15)
        ))

        assertTrue(validation.conditions[0].javaClass == Or::class.java)
        assertTrue(validation.conditions[0].baseRules[0].javaClass == EmailRule::class.java &&
                validation.conditions[0].baseRules[1].javaClass == MaxRule::class.java)
        assertTrue(validation.conditions[0].validate(sample))
        assertFalse(validation.conditions[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_MatchAllRules() {
        val sample = "hello@world.com"
        val invalidSample = "hello@world_hello@world"

        val validation = Validation(sample).matchAllRules(arrayOf(
                EmailRule(),
                MaxRule(15)
        ))

        assertTrue(validation.conditions[0].javaClass == And::class.java)
        assertTrue(validation.conditions[0].baseRules[0].javaClass == EmailRule::class.java &&
                validation.conditions[0].baseRules[1].javaClass == MaxRule::class.java)
        assertTrue(validation.conditions[0].validate(sample))
        assertFalse(validation.conditions[0].validate(invalidSample))
    }

    /**
     *  Extension for Condition
     */
    @Test
    @Throws(Exception::class)
    fun validate_Condition_Contain() {
        val sample = "contain test"
        val invalidSample = "contain Test"

        val condition = And().contain("test")

        assertTrue(condition.baseRules[0].javaClass == ContainRule::class.java)
        assertTrue(condition.baseRules[0].validate(sample))
        assertFalse(condition.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Condition_NotContain() {
        val sample = "contain Test"
        val invalidSample = "contain test"

        val condition = And().notContain("test")

        assertTrue(condition.baseRules[0].javaClass == NotContainRule::class.java)
        assertTrue(condition.baseRules[0].validate(sample))
        assertFalse(condition.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Condition_EqualTo() {
        val sample = "test"
        val invalidSample = "Test"

        val condition = And().equalTo("test")

        assertTrue(condition.baseRules[0].javaClass == EqualRule::class.java)
        assertTrue(condition.baseRules[0].validate(sample))
        assertFalse(condition.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Condition_NotEqualTo() {
        val sample = "Test"
        val invalidSample = "test"

        val condition = And().notEqualTo("test")

        assertTrue(condition.baseRules[0].javaClass == NotEqualRule::class.java)
        assertTrue(condition.baseRules[0].validate(sample))
        assertFalse(condition.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Condition_WithinRange() {
        val sample = "Hello World"
        val invalidSample = "Hello"

        val condition = And().withinRange(6, 15)

        assertTrue(condition.baseRules[0].javaClass == LengthRule::class.java)
        assertTrue(condition.baseRules[0].validate(sample))
        assertFalse(condition.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Condition_MinimumLength() {
        val sample = "Hello World"
        val invalidSample = "Hello"

        val condition = And().minimumLength(6)

        assertTrue(condition.baseRules[0].javaClass == MinRule::class.java)
        assertTrue(condition.baseRules[0].validate(sample))
        assertFalse(condition.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Condition_MaximumLength() {
        val sample = "Hello"
        val invalidSample = "Hello World"

        val condition = And().maximumLength(6)

        assertTrue(condition.baseRules[0].javaClass == MaxRule::class.java)
        assertTrue(condition.baseRules[0].validate(sample))
        assertFalse(condition.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Condition_Email() {
        val sample = "hello@world.com"
        val invalidSample = "hello_world.com"

        val condition = And().email()

        assertTrue(condition.baseRules[0].javaClass == EmailRule::class.java)
        assertTrue(condition.baseRules[0].validate(sample))
        assertFalse(condition.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Condition_AlphanumericOnly() {
        val sample = "helloworld123"
        val invalidSample = "hello world123"

        val condition = And().alphanumericOnly()

        assertTrue(condition.baseRules[0].javaClass == AlphanumericRule::class.java)
        assertTrue(condition.baseRules[0].validate(sample))
        assertFalse(condition.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Condition_AlphabetOnly() {
        val sample = "helloworld"
        val invalidSample = "hello world123"

        val condition = And().alphabetOnly()

        assertTrue(condition.baseRules[0].javaClass == AlphabetRule::class.java)
        assertTrue(condition.baseRules[0].validate(sample))
        assertFalse(condition.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Condition_DigitsOnly() {
        val sample = "123"
        val invalidSample = "hello world123"

        val condition = And().digitsOnly()

        assertTrue(condition.baseRules[0].javaClass == DigitsRule::class.java)
        assertTrue(condition.baseRules[0].validate(sample))
        assertFalse(condition.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Condition_SymbolsOnly() {
        val sample = "!@#"
        val invalidSample = "hello world123"

        val condition = And().symbolsOnly()

        assertEquals(condition.baseRules[0].getErrorMessage(), "Value does not contain symbols")
        assertTrue(condition.baseRules[0].validate(sample))
        assertFalse(condition.baseRules[0].validate(invalidSample))
    }

    @Test(expected = NullPointerException::class)
    @Throws(Exception::class)
    fun validate_Condition_SymbolsOnly_NPE() {
        val validation = And().symbolsOnly()

        assertFalse(validation.baseRules[0].validate(null))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Condition_AllUppercase() {
        val sample = "HELLO WORLD"
        val invalidSample = "hello world"

        val condition = And().allUppercase()

        assertEquals(condition.baseRules[0].getErrorMessage(), "Value is not all uppercase")
        assertTrue(condition.baseRules[0].validate(sample))
        assertFalse(condition.baseRules[0].validate(invalidSample))
    }

    @Test(expected = NullPointerException::class)
    @Throws(Exception::class)
    fun validate_Condition_AllUppercase_NPE() {
        val validation = And().allUppercase()

        assertFalse(validation.baseRules[0].validate(null))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Condition_AllLowercase() {
        val sample = "hello world"
        val invalidSample = "HELLO WORLD"

        val condition = And().allLowercase()

        assertEquals(condition.baseRules[0].getErrorMessage(), "Value is not all lowercase")
        assertTrue(condition.baseRules[0].validate(sample))
        assertFalse(condition.baseRules[0].validate(invalidSample))
    }

    @Test(expected = NullPointerException::class)
    @Throws(Exception::class)
    fun validate_Condition_AllLowercase_NPE() {
        val validation = And().allLowercase()

        assertFalse(validation.baseRules[0].validate(null))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Condition_StartsWith() {
        val sample = "hello world"
        val invalidSample = "world hello"

        val condition = And().startsWith("hello")

        assertEquals(condition.baseRules[0].getErrorMessage(), "Value is not start with hello")
        assertTrue(condition.baseRules[0].validate(sample))
        assertFalse(condition.baseRules[0].validate(invalidSample))
    }

    @Test(expected = NullPointerException::class)
    @Throws(Exception::class)
    fun validate_Condition_StartsWith_NPE() {
        val validation = And().startsWith("hello")

        assertFalse(validation.baseRules[0].validate(null))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Condition_EndsWith() {
        val sample = "hello world"
        val invalidSample = "world hello"

        val condition = And().endsWith("world")

        assertEquals(condition.baseRules[0].getErrorMessage(), "Value is not end with world")
        assertTrue(condition.baseRules[0].validate(sample))
        assertFalse(condition.baseRules[0].validate(invalidSample))
    }

    @Test(expected = NullPointerException::class)
    @Throws(Exception::class)
    fun validate_Condition_EndsWith_NPE() {
        val validation = And().endsWith("world")

        assertFalse(validation.baseRules[0].validate(null))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Condition_WithCreditCard() {
        val sample = "4111111111111111"
        val invalidSample = "5555555555554444"

        val condition = And().withCreditCard(CreditCardRule.CreditCardRegex.VISA)

        assertTrue(condition.baseRules[0].javaClass == CreditCardRule::class.java)
        assertTrue(condition.baseRules[0].validate(sample))
        assertFalse(condition.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Condition_WithPassword() {
        val sample = "abc ABC 123 .,"
        val invalidSample = "abc 123 ,./"

        val condition = And().withPassword(PasswordRule.PasswordRegex.ALPHA_MIXED_CASE)

        assertTrue(condition.baseRules[0].javaClass == PasswordRule::class.java)
        assertTrue(condition.baseRules[0].validate(sample))
        assertFalse(condition.baseRules[0].validate(invalidSample))
    }
}