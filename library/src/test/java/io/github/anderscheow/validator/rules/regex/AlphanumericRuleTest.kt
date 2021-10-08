package io.github.anderscheow.validator.rules.regex

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AlphanumericRuleTest {

    private lateinit var alphanumericRule: AlphanumericRule

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
        alphanumericRule = AlphanumericRule()

        alphanumericRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithAlphanumeric_ReturnTrue() {
        alphanumericRule = AlphanumericRule()

        val sample = "test123"

        assertTrue(alphanumericRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithAlphanumericAndSpace_ReturnFalse() {
        alphanumericRule = AlphanumericRule()

        val sample = "test 123"

        assertFalse(alphanumericRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithAlphanumericAndSymbols_ReturnFalse() {
        alphanumericRule = AlphanumericRule()

        val sample = "test 100,000,000"

        assertFalse(alphanumericRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithAlphabetOnly_ReturnTrue() {
        alphanumericRule = AlphanumericRule()

        val sample = "test"

        assertTrue(alphanumericRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithDigitsOnly_ReturnTrue() {
        alphanumericRule = AlphanumericRule()

        val sample = "123"

        assertTrue(alphanumericRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithSymbolsOnly_ReturnFalse() {
        alphanumericRule = AlphanumericRule()

        val sample = ",./"

        assertFalse(alphanumericRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = "Value does not match alphanumeric regex"

        alphanumericRule = AlphanumericRule()

        assertEquals(errorMessage, alphanumericRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        alphanumericRule = AlphanumericRule(errorMessage)

        assertEquals(errorMessage, alphanumericRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        alphanumericRule = AlphanumericRule(errorRes)

        assertEquals(errorRes, alphanumericRule.errorRes)
    }
}