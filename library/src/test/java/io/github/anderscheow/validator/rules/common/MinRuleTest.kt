package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class MinRuleTest {

    private lateinit var minRule: MinRule

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
        minRule = MinRule(MIN_LENGTH)

        minRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_IntSampleEqualMax_ReturnTrue() {
        minRule = MinRule(MIN_LENGTH)

        val sample = 12345

        assertTrue(minRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_IntSampleLessThanMax_ReturnFalse() {
        minRule = MinRule(MIN_LENGTH)

        val sample = 123

        assertFalse(minRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_IntSampleMoreThanMax_ReturnTrue() {
        minRule = MinRule(MIN_LENGTH)

        val sample = 1234567

        assertTrue(minRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_BoolSampleEqualMax_ReturnTrue() {
        minRule = MinRule(MIN_LENGTH)

        val sample = false

        assertTrue(minRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_BoolSampleMoreThanMax_ReturnFalse() {
        minRule = MinRule(MIN_LENGTH)

        val sample = true

        assertFalse(minRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSampleEqualMin_ReturnTrue() {
        minRule = MinRule(MIN_LENGTH)

        val sample = "tests"

        assertTrue(minRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSampleMoreThanMin_ReturnTrue() {
        minRule = MinRule(MIN_LENGTH)

        val sample = "testing"

        assertTrue(minRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSampleLessThanMin_ReturnTrue() {
        minRule = MinRule(MIN_LENGTH)

        val sample = "test"

        assertFalse(minRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = String.format(Locale.getDefault(), "Length must exceed at least %d characters", MIN_LENGTH)

        minRule = MinRule(MIN_LENGTH)

        assertEquals(errorMessage, minRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        minRule = MinRule(MIN_LENGTH, errorMessage)

        assertEquals(errorMessage, minRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        minRule = MinRule(MIN_LENGTH, errorRes)

        assertEquals(errorRes, minRule.getErrorRes())
    }

    companion object {

        private const val MIN_LENGTH = 5
    }
}