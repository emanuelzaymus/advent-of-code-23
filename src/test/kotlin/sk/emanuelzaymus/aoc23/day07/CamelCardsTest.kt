package sk.emanuelzaymus.aoc23.day07

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class CamelCardsTest {

    @Test
    fun getTotalWinningOfRankedHands() {
        val actual = getTotalWinningOfRankedHands(
            listOf(
                "32T3K 765",
                "T55J5 684",
                "KK677 28 ",
                "KTJJT 220",
                "QQQJA 483",
            )
        )

        assertEquals(6440, actual)
    }

    @Test
    fun sortByRankAscending() {
        val actual = listOf(
            CardHand("32T3K", 765),
            CardHand("T55J5", 684),
            CardHand("KK677", 28),
            CardHand("KTJJT", 220),
            CardHand("QQQJA", 483),
        ).sortByRankAscending()

        assertEquals(
            listOf(
                CardHand("32T3K", 765), // 1
                CardHand("KTJJT", 220), // 2
                CardHand("KK677", 28),  // 3
                CardHand("T55J5", 684), // 4
                CardHand("QQQJA", 483), // 5
            ),
            actual
        )
    }

}
