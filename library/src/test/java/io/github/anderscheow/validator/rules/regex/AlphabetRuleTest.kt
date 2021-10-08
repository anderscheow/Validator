package io.github.anderscheow.validator.rules.regex

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AlphabetRuleTest {

    private lateinit var alphabetRule: AlphabetRule

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
        alphabetRule = AlphabetRule()

        alphabetRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithAlphabet_ReturnTrue() {
        alphabetRule = AlphabetRule()

        val sample = "test"

        assertTrue(alphabetRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithAlphabetAndNumbers_ReturnFalse() {
        alphabetRule = AlphabetRule()

        val sample = "test 1000"

        assertFalse(alphabetRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithAlphabetAndSpace_ReturnFalse() {
        alphabetRule = AlphabetRule()

        val sample = "test test"

        assertFalse(alphabetRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithAlphabetAndSymbols_ReturnFalse() {
        alphabetRule = AlphabetRule()

        val sample = "test test.test"

        assertFalse(alphabetRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithDigitsOnly_ReturnFalse() {
        alphabetRule = AlphabetRule()

        val sample = "123"

        assertFalse(alphabetRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleWithSymbolsOnly_ReturnFalse() {
        alphabetRule = AlphabetRule()

        val sample = ",./"

        assertFalse(alphabetRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = "Value does not match alphabet regex"

        alphabetRule = AlphabetRule()

        assertEquals(errorMessage, alphabetRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        alphabetRule = AlphabetRule(errorMessage)

        assertEquals(errorMessage, alphabetRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        alphabetRule = AlphabetRule(errorRes)

        assertEquals(errorRes, alphabetRule.errorRes)
    }
}