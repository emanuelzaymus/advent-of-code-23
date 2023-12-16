package sk.emanuelzaymus.aoc23.day16

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class EnergizedTilesTest {

    @Test
    fun numberOfEnergizedTiles() {
        val actual = numberOfEnergizedTiles(
            """
                .|...\....
                |.-.\.....
                .....|-...
                ........|.
                ..........
                .........\
                ..../.\\..
                .-.-/..|..
                .|....-|.\
                ..//.|....
            """.trimIndent()
        )

        assertEquals(46, actual)
    }

}
