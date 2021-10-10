package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LengthRuleTest {

    private lateinit var lengthRule: LengthRule

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
        lengthRule = LengthRule(MIN_LENGTH, MAX_LENGTH, "")

        lengthRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSampleWithinMinMax_ReturnTrue() {
        lengthRule = LengthRule(MIN_LENGTH, MAX_LENGTH, "")

        val sample = "7 chars"

        assertTrue(lengthRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSampleLessThanMin_ReturnFalse() {
        lengthRule = LengthRule(MIN_LENGTH, MAX_LENGTH, "")

        val sample = "char"

        assertFalse(lengthRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_SampleMoreThanMax_ReturnFalse() {
        lengthRule = LengthRule(MIN_LENGTH, MAX_LENGTH, "")

        val sample = "more than 10 chars"

        assertFalse(lengthRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_MinLengthEqualToIntegerMinValue_ReturnFalse() {
        lengthRule = LengthRule(Int.MIN_VALUE, MAX_LENGTH, "")

        val sample = "testing"

        assertTrue(lengthRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_MinLengthEqualToIntegerMaxValue_ReturnFalse() {
        lengthRule = LengthRule(MIN_LENGTH, Int.MAX_VALUE, "")

        val sample = "testing"

        assertTrue(lengthRule.validate(sample))
    }

    @Test(expected = IllegalStateException::class)
    @Throws(Exception::class)
    fun validate_SampleSwitchMinMax_ThrowIllegalStateException() {
        lengthRule = LengthRule(MAX_LENGTH, MIN_LENGTH, "")

        val sample = "more than 10 chars"

        lengthRule.validate(sample)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        lengthRule = LengthRule(MIN_LENGTH, MAX_LENGTH, errorMessage)

        assertEquals(errorMessage, lengthRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        lengthRule = LengthRule(MIN_LENGTH, MAX_LENGTH, errorRes)

        assertEquals(errorRes, lengthRule.errorRes)
    }

    companion object {
        private const val MIN_LENGTH = 5
        private const val MAX_LENGTH = 10
    }
}