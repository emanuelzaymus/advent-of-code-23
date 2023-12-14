package sk.emanuelzaymus.aoc23.day10

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MazeTraversingTest {

    @Test
    fun traverseMazeLoop() {
        val maze = readMaze(
            """
                ......
                F---7.
                |...|.
                |...S.
                |...L7
                L----J
            """.trimIndent()
        )

        traverseMazeLoop(maze)

        maze.forEach { row ->
            row.forEach { position ->
                val shouldBePartOfPath = position.tile != Tile.NONE

                assertTrue(position.isPath == shouldBePartOfPath) {
                    "Position $position should have isPath == $shouldBePartOfPath."
                }
            }
        }
    }

}
