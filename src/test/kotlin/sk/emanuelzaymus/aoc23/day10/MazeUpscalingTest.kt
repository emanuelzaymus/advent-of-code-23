package sk.emanuelzaymus.aoc23.day10

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MazeUpscalingTest {

    @Test
    fun upscaleMaze() {
        val actual = upscaleMaze(
            readMaze(
                """
                    ..F7.
                    .FJ|.
                    FJ.L7
                    |F--J
                    LJ...
                """.trimIndent()
            )
        )

        val expected = """
            ...............
            .......F--7....
            .......|..|....
            .......|..|....
            ....F--J..|....
            ....|.....|....
            ....|.....|....
            .F--J.....L--7.
            .|...........|.
            .|...........|.
            .|..F--------J.
            .|..|..........
            .|..|..........
            .L--J..........
            ...............
        """.trimIndent()

        assertEquals(expected, actual.asString())

    }

}
