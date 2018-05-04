package io.github.anderscheow.validator.rules.regex

import android.support.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DigitsRuleTest {

    private lateinit var digitsRule: DigitsRule

    @Before
    @Throws(Exception::class)
    fun setUp() {
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }

    @Test(expected = NullPointerException::class)
    @Throws(Exception::class)
    fun validate_EmptySample_ThrowNullPointerException() {
        digitsRule = DigitsRule()

        digitsRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithDigitsOnly_ReturnTrue() {
        digitsRule = DigitsRule()

        val sample = "123"

        assertTrue(digitsRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithAlphanumeric_ReturnFalse() {
        digitsRule = DigitsRule()

        val sample = "abc123"

        assertFalse(digitsRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithAlphabetOnly_ReturnFalse() {
        digitsRule = DigitsRule()

        val sample = "abc"

        assertFalse(digitsRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithDigitsAndSymbols_ReturnFalse() {
        digitsRule = DigitsRule()

        val sample = "100,000,000"

        assertFalse(digitsRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = "Value does not match digits regex"

        digitsRule = DigitsRule()

        assertEquals(errorMessage, digitsRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        digitsRule = DigitsRule(errorMessage)

        assertEquals(errorMessage, digitsRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        digitsRule = DigitsRule(errorRes)

        assertEquals(errorRes, digitsRule.getErrorRes())
    }
}