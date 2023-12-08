package sk.emanuelzaymus.aoc23.day07

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import sk.emanuelzaymus.aoc23.day07.RankType.*

class CamelCardsTest {

    private val inputData = listOf(
        "32T3K 765",
        "T55J5 684",
        "KK677 28 ",
        "KTJJT 220",
        "QQQJA 483",
    )

    @Test
    fun `getTotalWinningOfRankedHands - without jokers - should return correct result`() {
        val actual = getTotalWinningOfRankedHands(inputData)

        assertEquals(6440, actual)
    }

    @Test
    fun `getTotalWinningOfRankedHands - with jokers - should return correct result`() {
        val actual = getTotalWinningOfRankedHands(inputData, withJokers = true)

        assertEquals(5905, actual)
    }

    @Test
    fun `sortByRankAscending - without jokers - should sort correctly`() {
        val actual = listOf(
            CardHand("32T3K", 765, withJokers = false),
            CardHand("T55J5", 684, withJokers = false),
            CardHand("KK677", 28, withJokers = false),
            CardHand("KTJJT", 220, withJokers = false),
            CardHand("QQQJA", 483, withJokers = false),
        ).sortByRankAscending()

        assertEquals(
            listOf(
                CardHand("32T3K", 765, false, ONE_PAIR, "32:3="),
                CardHand("KTJJT", 220, false, TWO_PAIRS, "=:;;:"),
                CardHand("KK677", 28, false, TWO_PAIRS, "==677"),
                CardHand("T55J5", 684, false, THREE_OF_A_KIND, ":55;5"),
                CardHand("QQQJA", 483, false, THREE_OF_A_KIND, "<<<;>"),
            ),
            actual
        )
    }

    @Test
    fun `sortByRankAscending - with jokers - should sort correctly`() {
        val actual = listOf(
            CardHand("32T3K", 765, withJokers = true),
            CardHand("T55J5", 684, withJokers = true),
            CardHand("KK677", 28, withJokers = true),
            CardHand("KTJJT", 220, withJokers = true),
            CardHand("QQQJA", 483, withJokers = true),
        ).sortByRankAscending()

        assertEquals(
            listOf(
                CardHand("32T3K", 765, true, ONE_PAIR, "32:3="),
                CardHand("KK677", 28, true, TWO_PAIRS, "==677"),
                CardHand("T55J5", 684, true, FOUR_OF_A_KIND, ":5515"),
                CardHand("QQQJA", 483, true, FOUR_OF_A_KIND, "<<<1>"),
                CardHand("KTJJT", 220, true, FOUR_OF_A_KIND, "=:11:"),
            ),
            actual
        )
    }

}
