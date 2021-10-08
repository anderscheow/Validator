package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class NotBlankRuleTest {

    private lateinit var notBlankRule: NotBlankRule

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
        notBlankRule = NotBlankRule()

        notBlankRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_ReturnTrue() {
        notBlankRule = NotBlankRule()

        val sample = "not blank"

        assertTrue(notBlankRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_ReturnFalse() {
        notBlankRule = NotBlankRule()

        val sample = ""

        assertFalse(notBlankRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SpaceSample_ReturnFalse() {
        notBlankRule = NotBlankRule()

        val sample = " "

        assertFalse(notBlankRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = "Value must not be empty"

        notBlankRule = NotBlankRule()

        assertEquals(errorMessage, notBlankRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        notBlankRule = NotBlankRule(errorMessage)

        assertEquals(errorMessage, notBlankRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        notBlankRule = NotBlankRule(errorRes)

        assertEquals(errorRes, notBlankRule.errorRes)
    }
}