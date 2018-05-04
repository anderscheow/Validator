package io.github.anderscheow.validator.rules.common

import android.support.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class ContainRuleTest {

    private lateinit var containRule: ContainRule

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
        containRule = ContainRule(STRING_KEYWORD)

        containRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_IntSample_ReturnTrue() {
        containRule = ContainRule(INT_KEYWORD)

        val sample = 1234567890

        assertTrue(containRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_IntSample_ReturnFalse() {
        containRule = ContainRule(INT_KEYWORD)

        val sample = 1237890

        assertFalse(containRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_BoolSample_ReturnTrue() {
        containRule = ContainRule(BOOL_KEYWORD)

        val sample = true

        assertTrue(containRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_BoolSample_ReturnFalse() {
        containRule = ContainRule(BOOL_KEYWORD)

        val sample = false

        assertFalse(containRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_WithIgnoreCase_ReturnTrue() {
        containRule = ContainRule(STRING_KEYWORD, true)

        val sample = "contain_TEST"

        assertTrue(containRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_WithoutIgnoreCase_ReturnTrue() {
        containRule = ContainRule(STRING_KEYWORD)

        val sample = "contain_test"

        assertTrue(containRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_WithoutIgnoreCase_ReturnFalse() {
        containRule = ContainRule(STRING_KEYWORD)

        val sample = "contain_TEST"

        assertFalse(containRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = String.format(Locale.getDefault(), "Value does not contain '%s'", STRING_KEYWORD)

        containRule = ContainRule(STRING_KEYWORD)

        assertEquals(errorMessage, containRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        containRule = ContainRule(STRING_KEYWORD, errorMessage)

        assertEquals(errorMessage, containRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        containRule = ContainRule(STRING_KEYWORD, errorRes)

        assertEquals(errorRes, containRule.getErrorRes())
    }

    companion object {

        private const val STRING_KEYWORD = "test"
        private const val INT_KEYWORD = "456"
        private const val BOOL_KEYWORD = "true"
    }
}