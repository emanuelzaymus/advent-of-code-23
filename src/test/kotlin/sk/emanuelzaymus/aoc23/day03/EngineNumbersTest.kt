package sk.emanuelzaymus.aoc23.day03

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class EngineNumbersTest {

    @Test
    fun sumOfSymbolAdjacentNumbers() {
        val actual = sumOfSymbolAdjacentNumbers(
            """
                467..114..
                ...*......
                ..35..633.
                ......#...
                617*......
                .....+.58.
                ..592.....
                ......755.
                ...$.*....
                .664.598..
            """.trimIndent()
        )

        assertEquals(4361, actual)
    }

}
