package io.github.anderscheow.validator.rules.regex

import androidx.annotation.StringRes
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CreditCardRuleTest {

    private lateinit var creditCardRule: CreditCardRule

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
        creditCardRule = CreditCardRule(CreditCardRule.CreditCardRegex.VISA)

        creditCardRule.validate(null)
    }

    @Test
    @Throws(Exception::class)
    fun validate_VisaSamples_ReturnTrue() {
        creditCardRule = CreditCardRule(CreditCardRule.CreditCardRegex.VISA)

        val samples = arrayOf("4111111111111111", "4012888888881881", "4222222222222")

        for (sample in samples) {
            assertTrue("This sample failed: $sample", creditCardRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun validate_MasterCardSamples_ReturnTrue() {
        creditCardRule = CreditCardRule(CreditCardRule.CreditCardRegex.MASTERCARD)

        val samples = arrayOf("5555555555554444", "5105105105105100")

        for (sample in samples) {
            assertTrue("This sample failed: $sample", creditCardRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun validate_AmericanExpressSamples_ReturnTrue() {
        creditCardRule = CreditCardRule(CreditCardRule.CreditCardRegex.AMERICAN_EXPRESS)

        val samples = arrayOf("378282246310005", "371449635398431", "378734493671000")

        for (sample in samples) {
            assertTrue("This sample failed: $sample", creditCardRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun validate_DinersClubSamples_ReturnTrue() {
        creditCardRule = CreditCardRule(CreditCardRule.CreditCardRegex.DINERS_CLUB)

        val samples = arrayOf("30569309025904", "38520000023237")

        for (sample in samples) {
            assertTrue("This sample failed: $sample", creditCardRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun validate_DiscoverSamples_ReturnTrue() {
        creditCardRule = CreditCardRule(CreditCardRule.CreditCardRegex.DISCOVER)

        val samples = arrayOf("6011111111111117", "6011000990139424")

        for (sample in samples) {
            assertTrue("This sample failed: $sample", creditCardRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun validate_JcbSamples_ReturnTrue() {
        creditCardRule = CreditCardRule(CreditCardRule.CreditCardRegex.JCB)

        val samples = arrayOf("3530111333300000", "3566002020360505")

        for (sample in samples) {
            assertTrue("This sample failed: $sample", creditCardRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun validate_ChinaUnionPaySamples_ReturnTrue() {
        creditCardRule = CreditCardRule(CreditCardRule.CreditCardRegex.CHINA_UNION_PAY)

        val samples = arrayOf("6276028610651858", "6223263470710627", "6270213860867471")

        for (sample in samples) {
            assertTrue("This sample failed: $sample", creditCardRule.validate(sample))
        }
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_DefaultErrorMessage() {
        val errorMessage = "Value does not match any credit card regex"

        creditCardRule = CreditCardRule(CreditCardRule.CreditCardRegex.VISA)

        assertEquals(errorMessage, creditCardRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorMessage() {
        val errorMessage = "This is a custom error message"

        creditCardRule = CreditCardRule(CreditCardRule.CreditCardRegex.VISA, errorMessage)

        assertEquals(errorMessage, creditCardRule.errorString)
    }

    @Test
    @Throws(Exception::class)
    fun errorMessage_CustomErrorRes() {
        @StringRes val errorRes = 0

        creditCardRule = CreditCardRule(CreditCardRule.CreditCardRegex.VISA, errorRes)

        assertEquals(errorRes, creditCardRule.errorRes)
    }
}