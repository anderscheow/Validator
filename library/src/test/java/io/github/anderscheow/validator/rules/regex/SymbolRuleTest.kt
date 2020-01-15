package io.github.anderscheow.validator.rules.regex

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SymbolRuleTest {

    private lateinit var symbolRule: SymbolRule

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
        symbolRule = SymbolRule()

        symbolRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithDigitsOnly_ReturnTrue() {
        symbolRule = SymbolRule()

        val sample = "123"

        assertFalse(symbolRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithAlphanumeric_ReturnFalse() {
        symbolRule = SymbolRule()

        val sample = "abc123"

        assertFalse(symbolRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithAlphabetOnly_ReturnFalse() {
        symbolRule = SymbolRule()

        val sample = "abc"

        assertFalse(symbolRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithDigitsAndSymbols_ReturnFalse() {
        symbolRule = SymbolRule()

        val sample = "100,000,000"

        assertFalse(symbolRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithSymbolsOnly_ReturnTrue() {
        symbolRule = SymbolRule()

        val sample = "!@#$%^"

        assertTrue(symbolRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = "Value does not match symbol regex"

        symbolRule = SymbolRule()

        assertEquals(errorMessage, symbolRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        symbolRule = SymbolRule(errorMessage)

        assertEquals(errorMessage, symbolRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        symbolRule = SymbolRule(errorRes)

        assertEquals(errorRes, symbolRule.getErrorRes())
    }
}