package io.github.anderscheow.validator.conditions.common

import androidx.annotation.StringRes
import io.github.anderscheow.validator.conditions.Condition
import io.github.anderscheow.validator.rules.common.MaxRule
import io.github.anderscheow.validator.rules.common.MinRule
import io.github.anderscheow.validator.rules.regex.DigitsRule
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class OrTest {

    private lateinit var or: Condition

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
    fun validate_MatchThreeConditionsOutOfThree_ReturnTrue() {
        or = Or(
            listOf(
                MinRule(5, ""), // match
                MaxRule(10, ""), // match
                DigitsRule("") // match
            ),
            ""
        )

        val sample = "1234567"

        assertTrue(or.validate(sample))
    }

    @Test
    @Throws(Exception::class)
    fun validate_MatchTwoConditionsOutOfThree_ReturnTrue() {
        or = Or(
            listOf(
                MinRule(5, ""), // A
                MaxRule(10, ""), // B
                DigitsRule("") // C
            ),
            ""
        )

        val samples = arrayOf(
            "abcde", // Match A,B
            "12345678901", // Match A,C
            "123"
        )// Match B,C

        for (sample in samples) {
            assertTrue("This sample failed: $sample", or.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun validate_MatchOneConditionOutOfThree_ReturnTrue() {
        or = Or(
            listOf(
                MinRule(5, ""), // A
                MaxRule(10, ""), // B
                DigitsRule("") // C
            ),
            ""
        )

        val samples = arrayOf(
            "abc 12", // Match A
            "test-123", // Match B
            "123456789012345"
        )// Match C

        for (sample in samples) {
            assertTrue("This sample failed: $sample", or.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun validate_MatchZeroConditionOutOfThree_ReturnFalse() {
        or = Or(
            listOf(
                MaxRule(10, ""), // A
                DigitsRule("") // B
            ),
            ""
        )

        val samples = arrayOf("testing 123, how are you")

        for (sample in samples) {
            assertFalse("This sample failed: $sample", or.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = "Does not match 'Or' condition"

        or = Or(MaxRule(10, ""), errorMessage)

        assertEquals(errorMessage, or.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        or = Or(MaxRule(10, ""), errorRes)

        assertEquals(errorRes, or.errorRes)
    }
}