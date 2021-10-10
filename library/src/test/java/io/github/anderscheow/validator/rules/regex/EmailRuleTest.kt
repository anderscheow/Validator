package io.github.anderscheow.validator.rules.regex

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class EmailRuleTest {

    private lateinit var emailRule: EmailRule

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
        emailRule = EmailRule("")

        emailRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_ValidSamples_ReturnTrue() {
        emailRule = EmailRule("")

        val samples = arrayOf("test@hotmail.com", "test@hotmail.co.uk", "test@hotmail.my")

        for (sample in samples) {
            assertTrue("This email failed: $sample", emailRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun validate_InvalidSamples_ReturnFalse() {
        emailRule = EmailRule("")

        val samples = arrayOf("test@hotmail.c", "test@hotmail.co.")

        for (sample in samples) {
            assertFalse("This email failed: $sample", emailRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        emailRule = EmailRule(errorMessage)

        assertEquals(errorMessage, emailRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        emailRule = EmailRule(errorRes)

        assertEquals(errorRes, emailRule.errorRes)
    }
}