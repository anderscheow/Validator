package io.github.anderscheow.validator.conditions

import android.support.annotation.StringRes
import io.github.anderscheow.validator.rules.common.MaxRule
import io.github.anderscheow.validator.rules.common.MinRule
import org.junit.After
import org.junit.Assert.assertEquals
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
        condition = object : Condition() {
            override fun validate(value: Any?): Boolean {
                return false
            }
        }.add(MinRule(5))

        assertEquals(1, condition.baseRules.size.toLong())
    }

    @Test
    @Throws(Exception::class)
    fun add_TwoBaseRule() {
        condition = object : Condition() {
            override fun validate(value: Any?): Boolean {
                return false
            }
        }.add(MinRule(5))
                .add(MaxRule(10))

        assertEquals(2, condition.baseRules.size.toLong())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = "Invalid input"

        condition = object : Condition() {
            override fun validate(value: Any?): Boolean {
                return false
            }
        }

        assertEquals(errorMessage, condition.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        condition = object : Condition(errorMessage) {
            override fun validate(value: Any?): Boolean {
                return false
            }
        }

        assertEquals(errorMessage, condition.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        condition = object : Condition(errorRes) {
            override fun validate(value: Any?): Boolean {
                return false
            }
        }

        assertEquals(errorRes, condition.getErrorRes())
    }
}