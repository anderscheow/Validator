package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class StartsWithRuleTest {

    private lateinit var startsWithRule: StartsWithRule

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
        startsWithRule = StartsWithRule(KEYWORD, "")

        startsWithRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_WithIgnoreCase_ReturnTrue() {
        startsWithRule = StartsWithRule(KEYWORD, "", true)

        val sample = "TEST_starts_with"

        assertTrue(startsWithRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_WithoutIgnoreCase_ReturnTrue() {
        startsWithRule = StartsWithRule(KEYWORD, "")

        val sample = "test_starts_with"

        assertTrue(startsWithRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_WithoutIgnoreCase_ReturnFalse() {
        startsWithRule = StartsWithRule(KEYWORD, "")

        val sample = "TEST_starts_with"

        assertFalse(startsWithRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        startsWithRule = StartsWithRule(KEYWORD, errorMessage)

        assertEquals(errorMessage, startsWithRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        startsWithRule = StartsWithRule(KEYWORD, errorRes)

        assertEquals(errorRes, startsWithRule.errorRes)
    }

    companion object {
        private const val KEYWORD = "test"
    }
}