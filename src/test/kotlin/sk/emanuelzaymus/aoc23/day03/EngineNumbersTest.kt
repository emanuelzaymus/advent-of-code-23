package sk.emanuelzaymus.aoc23.day03

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class EngineNumbersTest {

    private val exampleInput = """
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

    @Test
    fun sumOfSymbolAdjacentNumbers() {
        val actual = sumOfSymbolAdjacentNumbers(exampleInput)

        assertEquals(4361, actual)
    }

    @Test
    fun sumOfGearRatios() {
        val actual = sumOfGearRatios(exampleInput)

        assertEquals(467835, actual)
    }

}
