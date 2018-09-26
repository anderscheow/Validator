package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RegexRuleTest {

    private lateinit var regexRule: RegexRule

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
        regexRule = object : RegexRule(REGEX) {
        }

        regexRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_ValidSample_ReturnTrue() {
        regexRule = object : RegexRule(REGEX) {
        }

        val sample = "123"

        assertTrue(regexRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_InvalidSample_ReturnFalse() {
        regexRule = object : RegexRule(REGEX) {
        }

        val sample = "TEST"

        assertFalse(regexRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = "Does not match regex rule"

        regexRule = object : RegexRule(REGEX) {
        }

        assertEquals(errorMessage, regexRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        regexRule = object : RegexRule(REGEX, errorMessage) {
        }

        assertEquals(errorMessage, regexRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        regexRule = object : RegexRule(REGEX, errorRes) {
        }

        assertEquals(errorRes, regexRule.getErrorRes())
    }

    companion object {

        private const val REGEX = "\\d+"
    }
}