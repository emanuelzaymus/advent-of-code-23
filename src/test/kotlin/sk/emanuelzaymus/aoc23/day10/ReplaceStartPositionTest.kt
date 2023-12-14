package sk.emanuelzaymus.aoc23.day10

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ReplaceStartPositionTest {

    private val expected = """
            -L|-7
            7F-7|
            L|7||
            JL-J|
            LF-FF
        """.trimIndent()

    @Test
    fun `replaceStartPosition - connectable north and south - should replace with VERTICAL tile`() {
        val maze = readMaze(
            """
                -L|-7
                7F-7|
                L|7S|
                JL-J|
                LF-FF
            """.trimIndent()
        )

        replaceStartPosition(maze)

        assertEquals(expected, maze.asString())
    }

    @Test
    fun `replaceStartPosition - connectable east and west - should replace with HORIZONTAL tile`() {
        val maze = readMaze(
            """
                -L|-7
                7F-7|
                L|7||
                JLSJ|
                LF-FF
            """.trimIndent()
        )

        replaceStartPosition(maze)

        assertEquals(expected, maze.asString())
    }

    @Test
    fun `replaceStartPosition - connectable north and east - should replace with L tile`() {
        val maze = readMaze(
            """
                -L|-7
                7F-7|
                L|7||
                JS-J|
                LF-FF
            """.trimIndent()
        )

        replaceStartPosition(maze)

        assertEquals(expected, maze.asString())
    }

    @Test
    fun `replaceStartPosition - connectable north and west - should replace with J tile`() {
        val maze = readMaze(
            """
                -L|-7
                7F-7|
                L|7||
                JL-S|
                LF-FF
            """.trimIndent()
        )

        replaceStartPosition(maze)

        assertEquals(expected, maze.asString())
    }

    @Test
    fun `replaceStartPosition - connectable south and east - should replace with F tile`() {
        val maze = readMaze(
            """
                -L|-7
                7S-7|
                L|7||
                JL-J|
                LF-FF
            """.trimIndent()
        )

        replaceStartPosition(maze)

        assertEquals(expected, maze.asString())
    }

    @Test
    fun `replaceStartPosition - connectable south and west - should replace with 7 tile`() {
        val maze = readMaze(
            """
                -L|-7
                7F-S|
                L|7||
                JL-J|
                LF-FF
            """.trimIndent()
        )

        replaceStartPosition(maze)

        assertEquals(expected, maze.asString())
    }

}
