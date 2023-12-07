package sk.emanuelzaymus.aoc23.day06

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class BoatRacesTest {

    @Test
    fun multiplyNumberOfWaysToWinTheRace() {
        val actual = multiplyNumberOfWaysToWinTheRace(
            listOf(
                "Time:      7  15   30",
                "Distance:  9  40  200",
            )
        )

        assertEquals(288, actual)
    }

    @Test
    fun `calculateNumberOfWaysToWinTheRace - time 7, distance 9 - should return 4`() {
        val actual = calculateNumberOfWaysToWinTheRace(Race(7, 9))

        assertEquals(4, actual)
    }

    @Test
    fun `calculateNumberOfWaysToWinTheRace - time 15, distance 40 - should return 8`() {
        val actual = calculateNumberOfWaysToWinTheRace(Race(15, 40))

        assertEquals(8, actual)
    }

    @Test
    fun `calculateNumberOfWaysToWinTheRace - time 30, distance 200 - should return 9`() {
        val actual = calculateNumberOfWaysToWinTheRace(Race(30, 200))

        assertEquals(9, actual)
    }

    @Test
    fun `calculateOptionsToWinTheRace - time 7, distance 40 - should return 2, 3, 4, 5`() {
        val actual = calculateWinningOptionsForRace(Race(7, 9))

        val expected = listOf(
            WinningOption(2, 10),
            WinningOption(3, 12),
            WinningOption(4, 12),
            WinningOption(5, 10),
        )
        assertEquals(expected, actual)
    }

}
