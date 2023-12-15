package sk.emanuelzaymus.aoc23.day14

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class PlatformTest {

    @Test
    fun tiltPlatformNorth() {
        val platform = readPlatform(
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

        platform.tiltPlatformNorth()

        val expectedPlatform = """
            OOOO.#.O..
            OO..#....#
            OO..O##..O
            O..#.OO...
            ........#.
            ..#....#.#
            ..O..#.O.O
            ..O.......
            #....###..
            #....#....
        """.trimIndent()

        assertEquals(expectedPlatform, platform.asString())
    }

    @Test
    fun calculatePlatformLoad() {
        val platform = readPlatform(
            """
                OOOO.#.O..
                OO..#....#
                OO..O##..O
                O..#.OO...
                ........#.
                ..#....#.#
                ..O..#.O.O
                ..O.......
                #....###..
                #....#....
            """.trimIndent()
        )

        assertEquals(136, platform.calculatePlatformLoad())
    }

    @Test
    fun asString() {
        val input = """
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

        val platform = readPlatform(input)

        assertEquals(input, platform.asString())
    }

}
