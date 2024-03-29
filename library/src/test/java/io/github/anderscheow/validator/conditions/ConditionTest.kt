package io.github.anderscheow.validator.conditions

import androidx.annotation.StringRes
import io.github.anderscheow.validator.rules.common.MaxRule
import io.github.anderscheow.validator.rules.common.MinRule
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ConditionTest {

    private lateinit var condition: Condition

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
    fun add_OneBaseRule() {
        condition = object : Condition(MinRule(5, ""), "") {
            override fun validate(value: String?): Boolean {
                return false
            }
        }

        assertEquals(1, condition.rules.size.toLong())
    }

    @Test
    @Throws(Exception::class)
    fun add_TwoBaseRule() {
        condition = object : Condition(listOf(MinRule(5, ""), MaxRule(10, "")), "") {
            override fun validate(value: String?): Boolean {
                return false
            }
        }

        assertEquals(2, condition.rules.size.toLong())
    }

    @Test
    @Throws(Exception::class)
    fun addAll_TwoBaseRule() {
        condition = object : Condition(listOf(MinRule(5, ""), MaxRule(10, "")), "") {
            override fun validate(value: String?): Boolean {
                return false
            }
        }

        assertEquals(2, condition.rules.size.toLong())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        condition = object : Condition(MinRule(5, ""), errorMessage) {
            override fun validate(value: String?): Boolean {
                return false
            }
        }

        assertEquals(errorMessage, condition.errorString)
        assertTrue(condition.isErrorAvailable)
        assertTrue(condition.isErrorMessageAvailable)
        assertFalse(condition.isErrorResAvailable)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        condition = object : Condition(MinRule(5, ""), errorRes) {
            override fun validate(value: String?): Boolean {
                return false
            }
        }

        assertEquals(errorRes, condition.errorRes)
        assertTrue(condition.isErrorAvailable)
        assertTrue(condition.isErrorMessageAvailable)
        assertTrue(condition.isErrorResAvailable)
    }
}