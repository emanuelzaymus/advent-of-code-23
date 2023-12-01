package sk.emanuelzaymus.aoc23.day1

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CalibrationValuesDecryptionTest {

    @Test
    fun sumOfDecryptedValues() {
        val actual = sumOfDecryptedValues(
            listOf(
                "1abc2",
                "pqr3stu8vwx",
                "a1b2c3d4e5f",
                "treb7uchet",
            )
        )

        assertEquals(142, actual)
    }

}
