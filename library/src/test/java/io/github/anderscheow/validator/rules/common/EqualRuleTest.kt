package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class EqualRuleTest {

    private lateinit var equalRule: EqualRule

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
        equalRule = EqualRule(STRING_KEYWORD)

        equalRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_ReturnTrue() {
        equalRule = EqualRule(STRING_KEYWORD)

        val sample = "test"

        assertTrue(equalRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_ReturnFalse() {
        equalRule = EqualRule(STRING_KEYWORD)

        val sample = "TEST"

        assertFalse(equalRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = String.format(Locale.getDefault(), "Value does not equal to '%s'", STRING_KEYWORD)

        equalRule = EqualRule(STRING_KEYWORD)

        assertEquals(errorMessage, equalRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        equalRule = EqualRule(STRING_KEYWORD, errorMessage)

        assertEquals(errorMessage, equalRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        equalRule = EqualRule(STRING_KEYWORD, errorRes)

        assertEquals(errorRes, equalRule.errorRes)
    }

    companion object {

        private const val STRING_KEYWORD = "test"
        private const val INT_KEYWORD = 456
        private const val BOOL_KEYWORD = false
    }
}