package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class EndsWithRuleTest {

    private lateinit var endsWithRule: EndsWithRule

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
        endsWithRule = EndsWithRule(KEYWORD)

        endsWithRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_WithIgnoreCase_ReturnTrue() {
        endsWithRule = EndsWithRule(KEYWORD, true)

        val sample = "ends_with_TEST"

        assertTrue(endsWithRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_WithoutIgnoreCase_ReturnTrue() {
        endsWithRule = EndsWithRule(KEYWORD)

        val sample = "ends_with_test"

        assertTrue(endsWithRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_WithoutIgnoreCase_ReturnFalse() {
        endsWithRule = EndsWithRule(KEYWORD)

        val sample = "ends_with_TEST"

        assertFalse(endsWithRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = String.format(Locale.getDefault(), "Value does not end with '%s'", KEYWORD)

        endsWithRule = EndsWithRule(KEYWORD)

        assertEquals(errorMessage, endsWithRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        endsWithRule = EndsWithRule(KEYWORD, errorMessage)

        assertEquals(errorMessage, endsWithRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        endsWithRule = EndsWithRule(KEYWORD, errorRes)

        assertEquals(errorRes, endsWithRule.getErrorRes())
    }

    companion object {

        private const val KEYWORD = "test"
    }
}