package io.github.anderscheow.validator.rules

import android.support.annotation.StringRes
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BaseRuleTest {

    private lateinit var baseRule: BaseRule<String>

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

        baseRule = object : BaseRule<String>(errorMessage) {
            override fun validate(value: String?): Boolean {
                return false
            }
        }

        assertEquals(errorMessage, baseRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        baseRule = object : BaseRule<String>(errorMessage) {
            override fun validate(value: String?): Boolean {
                return false
            }
        }

        assertEquals(errorMessage, baseRule.getErrorMessage())
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        baseRule = object : BaseRule<String>(errorRes) {
            override fun validate(value: String?): Boolean {
                return false
            }
        }

        assertEquals(errorRes, baseRule.getErrorRes())
    }
}