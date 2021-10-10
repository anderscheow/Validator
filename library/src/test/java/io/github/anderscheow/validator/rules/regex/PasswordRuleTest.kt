package io.github.anderscheow.validator.rules.regex

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PasswordRuleTest {

    private lateinit var passwordRule: PasswordRule

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
        passwordRule = PasswordRule(PasswordRule.PasswordRegex.ANY, "")

        passwordRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_AnySamplesWithAnyRegex_ReturnTrue() {
        passwordRule = PasswordRule(PasswordRule.PasswordRegex.ANY, "")

        val samples = arrayOf(
            "abc ABC 123 .,/",
            "abc123,./",
            "abcABC123",
            "abcABC,./",
            "ABC123,./",
            "abcABC",
            "abc123",
            "abc,./",
            "ABC123",
            "ABC,./",
            "123,./",
            "abc",
            "ABC",
            "123",
            ",./",
            " "
        )

        for (sample in samples) {
            assertTrue("This password failed: $sample", passwordRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun validate_ValidSamplesWithAlphaRegex_ReturnTrue() {
        passwordRule = PasswordRule(PasswordRule.PasswordRegex.ALPHA, "")

        val samples = arrayOf("abcABC123", "abcABC", "abc123", "ABC123", "abc", "ABC", "123")

        for (sample in samples) {
            assertTrue("This password failed: $sample", passwordRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun validate_InvalidSamplesWithAlphaRegex_ReturnFalse() {
        passwordRule = PasswordRule(PasswordRule.PasswordRegex.ALPHA, "")

        val samples = arrayOf(
            "abc ABC 123 .,/",
            "abc123,./",
            "abcABC,./",
            "ABC123,./",
            "abc,./",
            "ABC,./",
            "123,./",
            ",./",
            " "
        )

        for (sample in samples) {
            assertFalse("This password failed: $sample", passwordRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun validate_ValidSamplesWithAlphaMixedCaseRegex_ReturnTrue() {
        passwordRule = PasswordRule(PasswordRule.PasswordRegex.ALPHA_MIXED_CASE, "")

        val samples = arrayOf("abc ABC 123 .,/", "abcABC123", "abcABC,./", "abcABC")

        for (sample in samples) {
            assertTrue("This password failed: $sample", passwordRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun validate_InvalidSamplesWithAlphaMixedCaseRegex_ReturnFalse() {
        passwordRule = PasswordRule(PasswordRule.PasswordRegex.ALPHA_MIXED_CASE, "")

        val samples = arrayOf(
            "abc123,./",
            "ABC123,./",
            "abc123",
            "abc,./",
            "ABC123",
            "ABC,./",
            "123,./",
            "abc",
            "ABC",
            "123",
            ",./",
            " "
        )

        for (sample in samples) {
            assertFalse("This password failed: $sample", passwordRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun validate_ValidSamplesWithNumericRegex_ReturnTrue() {
        passwordRule = PasswordRule(PasswordRule.PasswordRegex.NUMERIC, "")

        val samples = arrayOf("123")

        for (sample in samples) {
            assertTrue("This password failed: $sample", passwordRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun validate_InvalidSamplesWithNumericRegex_ReturnFalse() {
        passwordRule = PasswordRule(PasswordRule.PasswordRegex.NUMERIC, "")

        val samples = arrayOf(
            "abc ABC 123 .,/",
            "abc123,./",
            "abcABC123",
            "abcABC,./",
            "ABC123,./",
            "abcABC",
            "abc123",
            "abc,./",
            "ABC123",
            "ABC,./",
            "123,./",
            "abc",
            "ABC",
            ",./",
            " "
        )

        for (sample in samples) {
            assertFalse("This password failed: $sample", passwordRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun validate_ValidSamplesWithAlphaNumericRegex_ReturnTrue() {
        passwordRule = PasswordRule(PasswordRule.PasswordRegex.ALPHA_NUMERIC, "")

        val samples =
            arrayOf("abc ABC 123 .,/", "abc123,./", "abcABC123", "ABC123,./", "abc123", "ABC123")

        for (sample in samples) {
            assertTrue("This password failed: $sample", passwordRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun validate_InvalidSamplesWithAlphaNumericRegex_ReturnFalse() {
        passwordRule = PasswordRule(PasswordRule.PasswordRegex.ALPHA_NUMERIC, "")

        val samples = arrayOf(
            "abcABC,./",
            "abcABC",
            "abc,./",
            "ABC,./",
            "123,./",
            "abc",
            "ABC",
            "123",
            ",./",
            " "
        )

        for (sample in samples) {
            assertFalse("This password failed: $sample", passwordRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun validate_ValidSamplesWithAlphaNumericSymbolsRegex_ReturnTrue() {
        passwordRule = PasswordRule(PasswordRule.PasswordRegex.ALPHA_NUMERIC_SYMBOLS, "")

        val samples = arrayOf("abc ABC 123 .,/", "abc123,./", "ABC123,./")

        for (sample in samples) {
            assertTrue("This password failed: $sample", passwordRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun validate_InvalidSamplesWithAlphaNumericSymbolsRegex_ReturnFalse() {
        passwordRule = PasswordRule(PasswordRule.PasswordRegex.ALPHA_NUMERIC_SYMBOLS, "")

        val samples = arrayOf(
            "abcABC123",
            "abcABC,./",
            "abcABC",
            "abc123",
            "abc,./",
            "ABC123",
            "ABC,./",
            "123,./",
            "abc",
            "ABC",
            "123",
            ",./",
            " "
        )

        for (sample in samples) {
            assertFalse("This password failed: $sample", passwordRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        passwordRule = PasswordRule(PasswordRule.PasswordRegex.ANY, errorMessage)

        assertEquals(errorMessage, passwordRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        passwordRule = PasswordRule(PasswordRule.PasswordRegex.ANY, errorRes)

        assertEquals(errorRes, passwordRule.errorRes)
    }
}