package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class NotEmptyRuleTest {

    private lateinit var notEmptyRule: NotEmptyRule

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
        notEmptyRule = NotEmptyRule()

        notEmptyRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_ReturnTrue() {
        notEmptyRule = NotEmptyRule()

        val sample = "not empty"

        assertTrue(notEmptyRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_ReturnFalse() {
        notEmptyRule = NotEmptyRule()

        val sample = ""

        assertFalse(notEmptyRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SpaceSample_ReturnTrue() {
        notEmptyRule = NotEmptyRule()

        val sample = " "

        assertTrue(notEmptyRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = "Value must not be empty"

        notEmptyRule = NotEmptyRule()

        assertEquals(errorMessage, notEmptyRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        notEmptyRule = NotEmptyRule(errorMessage)

        assertEquals(errorMessage, notEmptyRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        notEmptyRule = NotEmptyRule(errorRes)

        assertEquals(errorRes, notEmptyRule.getErrorRes())
    }
}