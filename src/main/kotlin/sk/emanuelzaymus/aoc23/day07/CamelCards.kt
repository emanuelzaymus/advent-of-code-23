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

    println("Problem 2: $totalWinningWithJokers") // 250382098
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

private fun parseCardHands(lines: List<String>, withJokers: Boolean): List<CardHand> {
    return lines
        .map { line ->
            val (cards, bid) = line.split(" ")
            CardHand(cards, bid.toInt(), withJokers)
        }
}
