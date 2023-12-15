package sk.emanuelzaymus.aoc23.day14

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class PlatformLoadTest {

    @Test
    fun `calculateTiltedPlatformLoad - simple example - should return correct result`() {
        val actual = calculateTiltedPlatformLoad(
            """
                O....#....
                O.OO#....#
                .....##...
                OO.#O....O
                .O.....O#.
                O.#..O.#.#
                ..O..#O..O
                .......O..
                #....###..
                #OO..#....
            """.trimIndent()
        )

        assertEquals(136, actual)
    }

}
