package sk.emanuelzaymus.aoc23.day10

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MazeTest {

    @Test
    fun `findNumberOfStepsFromStartToFarthestPoint - simple maze - should return correct result`() {
        val actual = findNumberOfStepsFromStartToFarthestPoint(
            """
                .....
                .S-7.
                .|.|.
                .L-J.
                .....
            """.trimIndent()
        )

        assertEquals(4, actual)
    }

    @Test
    fun `findNumberOfStepsFromStartToFarthestPoint - simple maze with extra tiles - should return correct result`() {
        val actual = findNumberOfStepsFromStartToFarthestPoint(
            """
                -L|F7
                7S-7|
                L|7||
                -L-J|
                L|-JF
            """.trimIndent()
        )

        assertEquals(4, actual)
    }

    @Test
    fun `findNumberOfStepsFromStartToFarthestPoint - more complicated maze - should return correct result`() {
        val actual = findNumberOfStepsFromStartToFarthestPoint(
            """
                ..F7.
                .FJ|.
                SJ.L7
                |F--J
                LJ...
            """.trimIndent()
        )

        assertEquals(8, actual)
    }

    @Test
    fun `findNumberOfStepsFromStartToFarthestPoint - more complicated maze with extra tiles - should return correct result`() {
        val actual = findNumberOfStepsFromStartToFarthestPoint(
            """
                7-F7-
                .FJ|7
                SJLL7
                |F--J
                LJ.LJ
            """.trimIndent()
        )

        assertEquals(8, actual)
    }

}
