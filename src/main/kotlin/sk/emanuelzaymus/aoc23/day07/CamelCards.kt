package sk.emanuelzaymus.aoc23.day07

import java.io.File

/**
 * https://adventofcode.com/2023/day/7
 */
private fun main() {
    val lines = File("data/day07.txt").readLines()

    val totalWinning = getTotalWinningOfRankedHands(lines)

    println("Problem 1: $totalWinning") // 248569531
}

fun getTotalWinningOfRankedHands(lines: List<String>): Int {
    return parseCardHands(lines)
        .sortByRankAscending()
        .map { it.bid }
        .reduceIndexed { index, acc, cardBid ->
            acc + cardBid * (index + 1)
        }
}

fun List<CardHand>.sortByRankAscending(): List<CardHand> {
    return this
        .sortedWith(
            Comparator
                .comparingInt(CardHand::rankTypeStrength)
                .thenComparing(CardHand::sortableRepresentation)
        )
}

data class CardHand(val cards: String, val bid: Int) {
    val rankTypeStrength = RankType.determineRankType(cards).strength
    val sortableRepresentation = determineSortableRepresentation(cards)
}

private enum class RankType(val strength: Int) {
    FIVE_OF_A_KIND(7),
    FOUR_OF_A_KIND(6),
    FULL_HOUSE(5),
    THREE_OF_A_KIND(4),
    TWO_PAIRS(3),
    ONE_PAIR(2),
    HIGH_CARD(1);

    companion object {
        fun determineRankType(cards: String): RankType {
            val cardCounts = cards
                .groupingBy { it }
                .eachCount()
                .map { it.value }
                .sortedDescending()

            return when (cardCounts.size) {
                1 -> FIVE_OF_A_KIND
                2 -> if (cardCounts.first() == 4) FOUR_OF_A_KIND else FULL_HOUSE
                3 -> if (cardCounts.first() == 3) THREE_OF_A_KIND else TWO_PAIRS
                4 -> ONE_PAIR
                else -> HIGH_CARD
            }
        }
    }
}

private val sortableCardRepresentations = mapOf(
    '2' to Char(48).toString(),
    '3' to Char(49).toString(),
    '4' to Char(50).toString(),
    '5' to Char(51).toString(),
    '6' to Char(52).toString(),
    '7' to Char(53).toString(),
    '8' to Char(54).toString(),
    '9' to Char(55).toString(),
    'T' to Char(56).toString(),
    'J' to Char(57).toString(),
    'Q' to Char(58).toString(),
    'K' to Char(59).toString(),
    'A' to Char(60).toString(),
)

private fun determineSortableRepresentation(cards: String): String {
    return cards
        .asSequence()
        .joinToString("") { card ->
            sortableCardRepresentations
                .getOrElse(card) {
                    throw Exception("Card '$card' not found in sortableCardRepresentations")
                }
        }
}

private fun parseCardHands(lines: List<String>): List<CardHand> {
    return lines
        .map { line ->
            val (cards, bid) = line.split(" ")
            CardHand(cards, bid.toInt())
        }
}
