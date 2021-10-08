package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class NotNullRuleTest {

    private lateinit var notNullRule: NotNullRule

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
    fun validate_ValidSample_ReturnTrue() {
        notNullRule = NotNullRule()

        val sample = "test"

        assertTrue(notNullRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_InvalidSample_ReturnFalse() {
        notNullRule = NotNullRule()

        assertFalse(notNullRule.validate(null))
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = "Value must not be null"

        notNullRule = NotNullRule()

        assertEquals(errorMessage, notNullRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        notNullRule = NotNullRule(errorMessage)

        assertEquals(errorMessage, notNullRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        notNullRule = NotNullRule(errorRes)

        assertEquals(errorRes, notNullRule.errorRes)
    }
}