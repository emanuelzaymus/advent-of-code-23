package sk.emanuelzaymus.aoc23.day01

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CalibrationValuesDecryptionTest {

    @Test
    fun `simpleSumOfDecryptedValues - only digits - should return correct result`() {
        val actual = simpleSumOfDecryptedValues(
            listOf(
                "1abc2",
                "pqr3stu8vwx",
                "a1b2c3d4e5f",
                "treb7uchet",
            )
        )

        assertEquals(142, actual)
    }

    @Test
    fun `sumOfDecryptedValues - only digits - should return correct result`() {
        val actual = sumOfDecryptedValues(
            listOf(
                "1abc2",
                "pqr3stu8vwx",
                "a1b2c3d4e5f",
                "treb7uchet",
            ),
            digitNumberMap
        )

        assertEquals(142, actual)
    }

    @Test
    fun `sumOfDecryptedValues - digits with spelled numbers - should return correct result`() {
        val actual = sumOfDecryptedValues(
            listOf(
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen",
            ),
            digitAndWordNumberMap
        )

        assertEquals(281, actual)
    }

}
