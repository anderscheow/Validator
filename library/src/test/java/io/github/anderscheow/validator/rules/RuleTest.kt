package io.github.anderscheow.validator.rules

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RuleTest {

    private lateinit var rule: Rule

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
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = "Invalid input"

        rule = object : Rule() {
            override fun validate(value: String?): Boolean {
                return false
            }
        }

        assertEquals(errorMessage, rule.errorString)
        assertTrue(rule.isErrorAvailable)
        assertTrue(rule.isErrorMessageAvailable)
        assertFalse(rule.isErrorResAvailable)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        rule = object : Rule(errorMessage) {
            override fun validate(value: String?): Boolean {
                return false
            }
        }

        assertEquals(errorMessage, rule.errorString)
        assertTrue(rule.isErrorAvailable)
        assertTrue(rule.isErrorMessageAvailable)
        assertFalse(rule.isErrorResAvailable)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        rule = object : Rule(errorRes) {
            override fun validate(value: String?): Boolean {
                return false
            }
        }

        assertEquals(errorRes, rule.errorRes)
        assertTrue(rule.isErrorAvailable)
        assertTrue(rule.isErrorMessageAvailable)
        assertTrue(rule.isErrorResAvailable)
    }
}