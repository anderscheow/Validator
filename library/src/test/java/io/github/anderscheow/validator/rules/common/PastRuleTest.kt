package io.github.anderscheow.validator.rules.common

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class PastRuleTest {

    private lateinit var pastRule: PastRule

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
        pastRule = PastRule(VALID_DATE_FORMAT)

        pastRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_ValidDateFormatAndValidValue_ReturnTrue() {
        pastRule = PastRule(VALID_DATE_FORMAT)

        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DAY_OF_YEAR, -1)

        val sample = VALID_DATE_FORMAT.format(calendar.time)

        assertTrue(pastRule.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_ValidDateFormatAndInvalidValue_ReturnFalse() {
        pastRule = PastRule(VALID_DATE_FORMAT)

        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DAY_OF_YEAR, 1)

        val sample = VALID_DATE_FORMAT.format(calendar.time)

        assertFalse(pastRule.validate(sample))
    }

    @Test(expected = IllegalArgumentException::class)
    @Throws(Exception::class)
    fun validate_InvalidDateFormatAndValidValue_ThrowIllegalArgumentException() {
        pastRule = PastRule(SimpleDateFormat("abc/dd/ee"))

        val sample = "5/12/2017"

        pastRule.validate(sample)
    }

    @Test(expected = IllegalArgumentException::class)
    @Throws(Exception::class)
    fun validate_InvalidDateFormatAndInvalidValue_ThrowIllegalArgumentException() {
        pastRule = PastRule(SimpleDateFormat("abc/dd/ee"))

        val sample = "100/100/2017"

        pastRule.validate(sample)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = "Does not match past rule"

        pastRule = PastRule(VALID_DATE_FORMAT)

        assertEquals(errorMessage, pastRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        pastRule = PastRule(VALID_DATE_FORMAT, errorMessage)

        assertEquals(errorMessage, pastRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        pastRule = PastRule(VALID_DATE_FORMAT, errorRes)

        assertEquals(errorRes, pastRule.errorRes)
    }

    companion object {

        private val VALID_DATE_FORMAT = SimpleDateFormat("dd/MM/yyyy")
    }
}