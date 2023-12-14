package sk.emanuelzaymus.aoc23.day10

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MazeTest {

    @Test
    fun determinePositionsEnclosedInTheLoop() {
        val maze = readAndPrepareSimpleMaze(
            """
                .....
                F--7.
                |..|.
                |..L7
                S---J
            """.trimIndent()
        )

        maze.determinePositionsEnclosedInTheLoop()

        for (row in maze) {
            for (position in row) {
                if (position.isPath) {
                    assertTrue(position.isEnclosedInTheLoop == null)
                    continue
                }
                val (_, x, y) = position

                val shouldBeEnclosedInTheLoop = x == 2 && y == 1
                    || x == 2 && y == 2
                    || x == 3 && y == 1
                    || x == 3 && y == 2

                assertTrue(position.isEnclosedInTheLoop == shouldBeEnclosedInTheLoop) {
                    "Position $position should have isEnclosedInTheLoop == $shouldBeEnclosedInTheLoop."
                }
            }
        }
    }

    @Test
    fun `determinePositionsEnclosedInTheLoop - larger maze - should mark all enclosed positions`() {
        val maze = readAndPrepareSimpleMaze(
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

        maze.determinePositionsEnclosedInTheLoop()

        for (row in maze) {
            for (position in row) {
                if (position.isPath) {
                    assertTrue(position.isEnclosedInTheLoop == null)
                    continue
                }
                val (_, x, y) = position

                val shouldBeEnclosedInTheLoop = x == 6 && y == 2
                    || x == 6 && y == 3
                    || x == 6 && y == 7
                    || x == 6 && y == 8

                assertTrue(position.isEnclosedInTheLoop == shouldBeEnclosedInTheLoop) {
                    "Position $position should have isEnclosedInTheLoop == $shouldBeEnclosedInTheLoop."
                }
            }
        }
    }

    @Test
    fun `findNumberOfEnclosedTilesThatAreNotNextToPathTiles - too small maze - should return 0`() {
        val maze = readAndPrepareSimpleMaze(
            """
                .....
                F--7.
                |..|.
                |..LS
                L---J
            """.trimIndent()
        )

        val actual = maze.findNumberOfEnclosedTilesThatAreNotNextToPathTiles()

        assertEquals(0, actual)
    }

    @Test
    fun `findNumberOfEnclosedTilesThatAreNotNextToPathTiles - bigger maze - should return 1`() {
        val maze = readAndPrepareSimpleMaze(
            """
                ......
                F---7.
                |...|.
                |...S.
                |...L7
                L----J
            """.trimIndent()
        )

        val actual = maze.findNumberOfEnclosedTilesThatAreNotNextToPathTiles()

        assertEquals(1, actual)
    }

    private fun readAndPrepareSimpleMaze(mazeInput: String): Maze {
        return readMaze(mazeInput).also {

            traverseMazeLoop(it)

            replaceStartPosition(it)
        }
    }

}
