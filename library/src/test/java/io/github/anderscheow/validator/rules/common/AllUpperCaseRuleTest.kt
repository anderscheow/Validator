package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AllUpperCaseRuleTest {

    private lateinit var allUpperCaseRule: AllUpperCaseRule

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
        allUpperCaseRule = AllUpperCaseRule()

        allUpperCaseRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_ReturnTrue() {
        allUpperCaseRule = AllUpperCaseRule()

        val sample = "UPPERCASE"

        assertTrue(allUpperCaseRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_ReturnFalse() {
        allUpperCaseRule = AllUpperCaseRule()

        val sample = "lowercase"

        assertFalse(allUpperCaseRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_MixStringSample_ReturnFalse() {
        allUpperCaseRule = AllUpperCaseRule()

        val sample = "lowerUpper"

        assertFalse(allUpperCaseRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = "Value is not all uppercase"

        allUpperCaseRule = AllUpperCaseRule()

        assertEquals(errorMessage, allUpperCaseRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        allUpperCaseRule = AllUpperCaseRule(errorMessage)

        assertEquals(errorMessage, allUpperCaseRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        allUpperCaseRule = AllUpperCaseRule(errorRes)

        assertEquals(errorRes, allUpperCaseRule.getErrorRes())
    }
}