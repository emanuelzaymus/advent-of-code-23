package sk.emanuelzaymus.aoc23.day16

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class EnergizedTilesTest {

    private val input = """
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

    @Test
    fun numberOfEnergizedTiles() {
        val actual = numberOfEnergizedTiles(input)

        assertEquals(46, actual)
    }

    @Test
    fun numberOfEnergizedTilesWithBestConfiguration() {
        val actual = numberOfEnergizedTilesWithBestConfiguration(input)

        assertEquals(51, actual)
    }

}
