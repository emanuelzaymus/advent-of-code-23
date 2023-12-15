package sk.emanuelzaymus.aoc23.day14

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import sk.emanuelzaymus.aoc23.day14.Direction.NORTH

class PlatformTest {

    companion object {
        val initialPlatformInput = """
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
    }

    @Test
    fun `tiltPlatformInDirection - north - should tilt all the rocks`() {
        val platform = readPlatform(initialPlatformInput)

        platform.tiltPlatformInDirection(NORTH)

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
    fun `tiltWholeCycle - tilt 1 cycle - should tilt correctly`() {
        val platform = readPlatform(initialPlatformInput)

        platform.tiltWholeCycle()

        val expectedPlatform = """
            .....#....
            ....#...O#
            ...OO##...
            .OO#......
            .....OOO#.
            .O#...O#.#
            ....O#....
            ......OOOO
            #...O###..
            #..OO#....
        """.trimIndent()

        assertEquals(expectedPlatform, platform.asString())
    }

    @Test
    fun `tiltWholeCycle - tilt 2 cycles - should tilt correctly`() {
        val platform = readPlatform(initialPlatformInput)

        repeat(2) {
            platform.tiltWholeCycle()
        }

        val expectedPlatform = """
            .....#....
            ....#...O#
            .....##...
            ..O#......
            .....OOO#.
            .O#...O#.#
            ....O#...O
            .......OOO
            #..OO###..
            #.OOO#...O
        """.trimIndent()

        assertEquals(expectedPlatform, platform.asString())
    }

    @Test
    fun `tiltWholeCycle - tilt 3 cycles - should tilt correctly`() {
        val platform = readPlatform(initialPlatformInput)

        repeat(3) {
            platform.tiltWholeCycle()
        }

        val expectedPlatform = """
            .....#....
            ....#...O#
            .....##...
            ..O#......
            .....OOO#.
            .O#...O#.#
            ....O#...O
            .......OOO
            #...O###.O
            #.OOO#...O
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
        val platform = readPlatform(initialPlatformInput)

        assertEquals(initialPlatformInput, platform.asString())
    }

}
