package io.github.anderscheow.validator.rules.common

import android.support.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

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

    @Test
    @Throws(Exception::class)
    fun validate_IntSample_ReturnTrue() {
        notEqualRule = NotEqualRule(INT_KEYWORD)

        val sample = 123

        assertTrue(notEqualRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_IntSample_ReturnFalse() {
        notEqualRule = NotEqualRule(INT_KEYWORD)

        val sample = 456

        assertFalse(notEqualRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_BoolSample_ReturnTrue() {
        notEqualRule = NotEqualRule(BOOL_KEYWORD)

        val sample = true

        assertTrue(notEqualRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_BoolSample_ReturnFalse() {
        notEqualRule = NotEqualRule(BOOL_KEYWORD)

        val sample = false

        assertFalse(notEqualRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_ReturnTrue() {
        notEqualRule = NotEqualRule(STRING_KEYWORD)

        val sample = "TEST"

        assertTrue(notEqualRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_ReturnFalse() {
        notEqualRule = NotEqualRule(STRING_KEYWORD)

        val sample = "test"

        assertFalse(notEqualRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = String.format(Locale.getDefault(), "Value equal to '%s'", STRING_KEYWORD)

        notEqualRule = NotEqualRule(STRING_KEYWORD)

        assertEquals(errorMessage, notEqualRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        notEqualRule = NotEqualRule(STRING_KEYWORD, errorMessage)

        assertEquals(errorMessage, notEqualRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        notEqualRule = NotEqualRule(STRING_KEYWORD, errorRes)

        assertEquals(errorRes, notEqualRule.getErrorRes())
    }

    companion object {

        private const val STRING_KEYWORD = "test"
        private const val INT_KEYWORD = 456
        private const val BOOL_KEYWORD = false
    }
}