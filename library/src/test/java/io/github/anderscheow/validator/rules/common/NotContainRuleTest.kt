package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class NotContainRuleTest {

    private lateinit var notContainRule: NotContainRule

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
        notContainRule = NotContainRule(STRING_KEYWORD)

        notContainRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_ReturnTrue() {
        notContainRule = NotContainRule(STRING_KEYWORD)

        val sample = "contain_TEST"

        assertTrue(notContainRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_ReturnFalse() {
        notContainRule = NotContainRule(STRING_KEYWORD)

        val sample = "contain_test"

        assertFalse(notContainRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = String.format(Locale.getDefault(), "Value does contain '%s'", STRING_KEYWORD)

        notContainRule = NotContainRule(STRING_KEYWORD)

        assertEquals(errorMessage, notContainRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        notContainRule = NotContainRule(STRING_KEYWORD, errorMessage)

        assertEquals(errorMessage, notContainRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        notContainRule = NotContainRule(STRING_KEYWORD, errorRes)

        assertEquals(errorRes, notContainRule.errorRes)
    }

    companion object {

        private const val STRING_KEYWORD = "test"
        private const val INT_KEYWORD = "456"
        private const val BOOL_KEYWORD = "true"
    }
}