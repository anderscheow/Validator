package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class MaxRuleTest {

    private lateinit var maxRule: MaxRule

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
        maxRule = MaxRule(MAX_LENGTH)

        maxRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_IntSampleEqualMax_ReturnTrue() {
        maxRule = MaxRule(MAX_LENGTH)

        val sample = 1234

        assertTrue(maxRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_IntSampleLessThanMax_ReturnTrue() {
        maxRule = MaxRule(MAX_LENGTH)

        val sample = 123

        assertTrue(maxRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_IntSampleMoreThanMax_ReturnFalse() {
        maxRule = MaxRule(MAX_LENGTH)

        val sample = 1234567

        assertFalse(maxRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_BoolSampleEqualMax_ReturnTrue() {
        maxRule = MaxRule(MAX_LENGTH)

        val sample = true

        assertTrue(maxRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_BoolSampleMoreThanMax_ReturnFalse() {
        maxRule = MaxRule(MAX_LENGTH)

        val sample = false

        assertFalse(maxRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSampleEqualMax_ReturnTrue() {
        maxRule = MaxRule(MAX_LENGTH)

        val sample = "test"

        assertTrue(maxRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSampleLessThanMax_ReturnTrue() {
        maxRule = MaxRule(MAX_LENGTH)

        val sample = "tes"

        assertTrue(maxRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleMoreThanMax_ReturnTrue() {
        maxRule = MaxRule(MAX_LENGTH)

        val sample = "testing"

        assertFalse(maxRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = String.format(Locale.getDefault(), "Length must not exceed %d characters", MAX_LENGTH)

        maxRule = MaxRule(MAX_LENGTH)

        assertEquals(errorMessage, maxRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        maxRule = MaxRule(MAX_LENGTH, errorMessage)

        assertEquals(errorMessage, maxRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        maxRule = MaxRule(MAX_LENGTH, errorRes)

        assertEquals(errorRes, maxRule.getErrorRes())
    }

    companion object {

        private const val MAX_LENGTH = 4
    }
}