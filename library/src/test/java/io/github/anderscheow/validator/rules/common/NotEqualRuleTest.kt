package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class NotEqualRuleTest {

    private lateinit var notEqualRule: NotEqualRule

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
        notEqualRule = NotEqualRule(INT_KEYWORD, "")

        notEqualRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_ReturnTrue() {
        notEqualRule = NotEqualRule(STRING_KEYWORD, "")

        val sample = "TEST"

        assertTrue(notEqualRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_ReturnFalse() {
        notEqualRule = NotEqualRule(STRING_KEYWORD, "")

        val sample = "test"

        assertFalse(notEqualRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        notEqualRule = NotEqualRule(STRING_KEYWORD, errorMessage)

        assertEquals(errorMessage, notEqualRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        notEqualRule = NotEqualRule(STRING_KEYWORD, errorRes)

        assertEquals(errorRes, notEqualRule.errorRes)
    }

    companion object {
        private const val STRING_KEYWORD = "test"
        private const val INT_KEYWORD = 456
    }
}