package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AllLowerCaseRuleTest {

    private lateinit var allLowerCaseRule: AllLowerCaseRule

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
        allLowerCaseRule = AllLowerCaseRule()

        allLowerCaseRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_IntSample_ReturnFalse() {
        allLowerCaseRule = AllLowerCaseRule()

        val sample = 123

        assertTrue(allLowerCaseRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_BoolSample_ReturnFalse() {
        allLowerCaseRule = AllLowerCaseRule()

        val sample = false

        assertTrue(allLowerCaseRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_ReturnTrue() {
        allLowerCaseRule = AllLowerCaseRule()

        val sample = "lowercase"

        assertTrue(allLowerCaseRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_StringSample_ReturnFalse() {
        allLowerCaseRule = AllLowerCaseRule()

        val sample = "UPPERCASE"

        assertFalse(allLowerCaseRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_MixStringSample_ReturnFalse() {
        allLowerCaseRule = AllLowerCaseRule()

        val sample = "lowerUpper"

        assertFalse(allLowerCaseRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = "Value is not all lowercase"

        allLowerCaseRule = AllLowerCaseRule()

        assertEquals(errorMessage, allLowerCaseRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        allLowerCaseRule = AllLowerCaseRule(errorMessage)

        assertEquals(errorMessage, allLowerCaseRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        allLowerCaseRule = AllLowerCaseRule(errorRes)

        assertEquals(errorRes, allLowerCaseRule.getErrorRes())
    }
}