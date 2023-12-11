package sk.emanuelzaymus.aoc23.day10

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MazeProblemsTest {

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

    @Test
    fun `findNumberOfEnclosedTiles - with no enclosed outside tiles - should return correct result`() {
        val actual = findNumberOfEnclosedTiles(
            """
                ...........
                .S-------7.
                .|F-----7|.
                .||.....||.
                .||.....||.
                .|L-7.F-J|.
                .|..|.|..|.
                .L--J.L--J.
                ...........
            """.trimIndent()
        )

        assertEquals(4, actual)
    }

    @Test
    fun `findNumberOfEnclosedTiles - with enclosed outside tiles - should ignore them`() {
        val actual = findNumberOfEnclosedTiles(
            """
                ..........
                .S------7.
                .|F----7|.
                .||....||.
                .||....||.
                .|L-7F-J|.
                .|..||..|.
                .L--JL--J.
                ..........
            """.trimIndent()
        )

        assertEquals(4, actual)
    }

    @Test
    fun `findNumberOfEnclosedTiles - larger maze with only dots as empty tiles - should return correct result`() {
        val actual = findNumberOfEnclosedTiles(
            """
                .F----7F7F7F7F-7....
                .|F--7||||||||FJ....
                .||.FJ||||||||L7....
                FJL7L7LJLJ||LJ.L-7..
                L--J.L7...LJS7F-7L7.
                ....F-J..F7FJ|L7L7L7
                ....L7.F7||L7|.L7L7|
                .....|FJLJ|FJ|F7|.LJ
                ....FJL-7.||.||||...
                ....L---J.LJ.LJLJ...
            """.trimIndent()
        )

        assertEquals(8, actual)
    }

    @Test
    fun `findNumberOfEnclosedTiles - larger maze with not connected pipes - should return correct result`() {
        val actual = findNumberOfEnclosedTiles(
            """
                FF7FSF7F7F7F7F7F---7
                L|LJ||||||||||||F--J
                FL-7LJLJ||||||LJL-77
                F--JF--7||LJLJ7F7FJ-
                L---JF-JLJ.||-FJLJJ7
                |F|F-JF---7F7-L7L|7|
                |FFJF7L7F-JF7|JL---7
                7-L-JL7||F7|L7F-7F7|
                L.L7LFJ|||||FJL7||LJ
                L7JLJL-JLJLJL--JLJ.L
            """.trimIndent()
        )

        assertEquals(10, actual)
    }

}
