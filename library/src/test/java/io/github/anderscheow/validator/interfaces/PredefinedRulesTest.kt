package io.github.anderscheow.validator.interfaces

import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.conditions.common.And
import io.github.anderscheow.validator.conditions.common.Or
import io.github.anderscheow.validator.conditions.common.matchAllRules
import io.github.anderscheow.validator.conditions.common.matchAtLeastOneRule
import io.github.anderscheow.validator.rules.common.*
import io.github.anderscheow.validator.rules.regex.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

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
    fun validate_Validation_Future() {
        val sample = "31/12/${Calendar.getInstance().get(Calendar.YEAR) + 1}"

        val validation = Validation(sample).future(SimpleDateFormat("dd/MM/yyyy"))

        assertTrue(validation.baseRules[0].javaClass == FutureRule::class.java)
        assertTrue(validation.baseRules[0].validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_Past() {
        val sample = "31/12/${Calendar.getInstance().get(Calendar.YEAR) - 1}"

        val validation = Validation(sample).past(SimpleDateFormat("dd/MM/yyyy"))

        assertTrue(validation.baseRules[0].javaClass == PastRule::class.java)
        assertTrue(validation.baseRules[0].validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_NotEmpty() {
        val sample = " "
        val invalidSample = ""

        val validation = Validation(sample).notEmpty()

        assertTrue(validation.baseRules[0].javaClass == NotEmptyRule::class.java)
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_NotBlank() {
        val sample = "not blank"
        val invalidSample = " "

        val validation = Validation(sample).notBlank()

        assertTrue(validation.baseRules[0].javaClass == NotBlankRule::class.java)
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_NotNull() {
        val sample = "not null"
        val invalidSample = null

        val validation = Validation(sample).notNull()

        assertTrue(validation.baseRules[0].javaClass == NotNullRule::class.java)
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_Regex() {
        val sample = "hello@world.com"
        val invalidSample = "hello_world.com"

        val validation = Validation(sample).regex("\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\\b")

        assertTrue(validation.baseRules[0].javaClass == RegexRule::class.java)
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

        assertEquals(validation.baseRules[0].errorString, "Value does not contain symbols")
        assertTrue(validation.baseRules[0].validate(sample))
        assertFalse(validation.baseRules[0].validate(invalidSample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_Validation_AllUppercase() {
        val sample = "HELLO WORLD"
        val invalidSample = "hello world"

        val validation = Validation(sample).allUppercase()

        assertEquals(validation.baseRules[0].errorString, "Value is not all uppercase")
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

        assertEquals(validation.baseRules[0].errorString, "Value is not all lowercase")
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

        assertEquals(validation.baseRules[0].errorString, "Value is not start with hello")
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

        assertEquals(validation.baseRules[0].errorString, "Value is not end with world")
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
}