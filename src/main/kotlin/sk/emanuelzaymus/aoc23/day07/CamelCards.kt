package sk.emanuelzaymus.aoc23.day07

import java.io.File

/**
 * https://adventofcode.com/2023/day/7
 */
private fun main() {
    val lines = File("data/day07.txt").readLines()

    val totalWinning = getTotalWinningOfRankedHands(lines)

    println("Problem 1: $totalWinning") // 248569531

    val totalWinningWithJokers = getTotalWinningOfRankedHands(lines, withJokers = true)

    println("Problem 2: $totalWinningWithJokers") // 250932589 - too high !!!
}

fun getTotalWinningOfRankedHands(lines: List<String>, withJokers: Boolean = false): Int {
    return parseCardHands(lines, withJokers)
        .sortByRankAscending()
        .onEachIndexed { i, cardHand -> println("$i -> $cardHand") }
        .map { it.bid }
        .reduceIndexed { index, acc, cardBid ->
            acc + cardBid * (index + 1)
        }
}

fun List<CardHand>.sortByRankAscending(): List<CardHand> {
    return this
        .sortedWith(
            Comparator
                .comparingInt<CardHand> { it.rankTypeStrength.strength }
                .thenComparing(CardHand::sortableRepresentation)
        )
}

data class CardHand(
    val cards: String,
    val bid: Int,
    val withJokers: Boolean,
    val rankTypeStrength: RankType = RankType.determineRankType(cards, withJokers),
    val sortableRepresentation: String = determineSortableRepresentation(cards, withJokers)
)

enum class RankType(val strength: Int) {
    FIVE_OF_A_KIND(7),
    FOUR_OF_A_KIND(6),
    FULL_HOUSE(5),
    THREE_OF_A_KIND(4),
    TWO_PAIRS(3),
    ONE_PAIR(2),
    HIGH_CARD(1);

    companion object {
        fun determineRankType(cards: String, withJokers: Boolean): RankType {
            return if (withJokers) determineRankTypeWithJokers(cards)
            else determineRankType(cards)
        }

        private fun determineRankType(cards: String): RankType {
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
                5 -> HIGH_CARD
                else -> throw UnexpectedCardCountException(cardCounts)
            }
        }

        private fun determineRankTypeWithJokers(cards: String): RankType {
            var jokerCount: Int

            val cardCounts = cards
                .groupingBy { it }
                .eachCount()
                .let { map ->
                    jokerCount = map.getOrDefault('J', 0)
                    map - 'J'
                }
                .map { it.value }
                .sortedDescending()

            if (jokerCount == 0) {
                return when (cardCounts.size) {
                    1 -> FIVE_OF_A_KIND
                    2 -> if (cardCounts.first() == 4) FOUR_OF_A_KIND else FULL_HOUSE
                    3 -> if (cardCounts.first() == 3) THREE_OF_A_KIND else TWO_PAIRS
                    4 -> ONE_PAIR
                    5 -> HIGH_CARD
                    else -> throw UnexpectedCardCountException(cardCounts)
                }
            }

            val firstCardCount by lazy { cardCounts.first() }

            return when (cardCounts.size) {
                0 -> FIVE_OF_A_KIND

                1 -> when {
                    firstCardCount == 4 && jokerCount == 1 -> FIVE_OF_A_KIND
                    firstCardCount == 3 && jokerCount == 2 -> FIVE_OF_A_KIND
                    firstCardCount == 2 && jokerCount == 3 -> FIVE_OF_A_KIND
                    firstCardCount == 1 && jokerCount == 4 -> FIVE_OF_A_KIND
                    firstCardCount == 0 && jokerCount == 5 -> FIVE_OF_A_KIND
                    else -> throw UnexpectedCardCountException(cardCounts, jokerCount)
                }

                2 -> when {
                    firstCardCount == 3 /* 1 */ && jokerCount == 1 -> FOUR_OF_A_KIND
                    firstCardCount == 2 /* 2 */ && jokerCount == 1 -> FULL_HOUSE
                    firstCardCount == 2 /* 1 */ && jokerCount == 2 -> FOUR_OF_A_KIND
                    firstCardCount == 1 /* 1 */ && jokerCount == 3 -> FOUR_OF_A_KIND
                    else -> throw UnexpectedCardCountException(cardCounts, jokerCount)
                }

                3 -> when {
                    firstCardCount == 2 /* 1 1 */ && jokerCount == 1 -> THREE_OF_A_KIND
                    firstCardCount == 1 /* 1 1 */ && jokerCount == 2 -> THREE_OF_A_KIND
                    else -> throw UnexpectedCardCountException(cardCounts, jokerCount)
                }

                4 -> when {
                    firstCardCount == 1 /* 1 1 1 */ && jokerCount == 1 -> ONE_PAIR
                    else -> throw UnexpectedCardCountException(cardCounts, jokerCount)
                }

                else -> throw UnexpectedCardCountException(cardCounts, jokerCount)
            }
        }

        class UnexpectedCardCountException(cardCounts: List<Int>, jokerCount: Int? = null) :
            Exception("Unexpected cardCounts: $cardCounts and jokerCount: $jokerCount")
    }
}

private val sortableCardRepresentations = mapOf(
    '2' to Char(50).toString(), // 2
    '3' to Char(51).toString(), // 3
    '4' to Char(52).toString(), // 4
    '5' to Char(53).toString(), // 5
    '6' to Char(54).toString(), // 6
    '7' to Char(55).toString(), // 7
    '8' to Char(56).toString(), // 8
    '9' to Char(57).toString(), // 9
    'T' to Char(58).toString(), // :
    'J' to Char(59).toString(), // ;
    'Q' to Char(60).toString(), // <
    'K' to Char(61).toString(), // =
    'A' to Char(62).toString(), // >
)

private val sortableCardRepresentationsWithJoker = mapOf(
    'J' to Char(49).toString(), // 1
    '2' to Char(50).toString(), // 2
    '3' to Char(51).toString(), // 3
    '4' to Char(52).toString(), // 4
    '5' to Char(53).toString(), // 5
    '6' to Char(54).toString(), // 6
    '7' to Char(55).toString(), // 7
    '8' to Char(56).toString(), // 8
    '9' to Char(57).toString(), // 9
    'T' to Char(58).toString(), // :

    'Q' to Char(60).toString(), // <
    'K' to Char(61).toString(), // =
    'A' to Char(62).toString(), // >
)

private fun determineSortableRepresentation(cards: String, withJokers: Boolean): String {
    return cards
        .asSequence()
        .joinToString("") { card ->
            (if (withJokers) sortableCardRepresentationsWithJoker else sortableCardRepresentations)
                .getOrElse(card) {
                    throw Exception("Card '$card' not found in sortableCardRepresentations")
                }
        }
}

private fun parseCardHands(lines: List<String>, withJokers: Boolean): List<CardHand> {
    return lines
        .map { line ->
            val (cards, bid) = line.split(" ")
            CardHand(cards, bid.toInt(), withJokers)
        }
}
