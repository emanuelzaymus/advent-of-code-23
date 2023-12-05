package sk.emanuelzaymus.aoc23.day04

import java.io.File
import kotlin.math.pow

/**
 * https://adventofcode.com/2023/day/4
 */
private fun main() {
    val lines = File("data/day04.txt").readLines()

    val sum = sumOfScratchcardPoints(lines)

    println("Problem 1: $sum") // 20667

    val count = countWonScratchcards(lines)

    println("Problem 2: $count") // 5833065
}

fun sumOfScratchcardPoints(scratchcardLines: List<String>): Int {
    return scratchcardLines
        .sumOf { cardLine ->
            val count = getCountOfMatchingNumbers(cardLine)
            if (count > 0) 2.0.pow(count - 1).toInt() else 0
        }
}

private fun getCountOfMatchingNumbers(cardLine: String): Int {
    val split = cardLine.split(':', '|')

    val winningNumbers = extractNumbers(split[1])
    val myNumbers = extractNumbers(split[2])

    return myNumbers.count { it in winningNumbers }
}

private fun extractNumbers(str: String): List<Int> {
    return str
        .split(' ')
        .filter { it.isNotBlank() }
        .map { it.toInt() }
}

private data class ScratchcardWithCount(val matchesCount: Int, var count: Int = 1)

fun countWonScratchcards(scratchcardLines: List<String>): Int {
    val allCards = scratchcardLines
        .map { cardLine ->
            val count = getCountOfMatchingNumbers(cardLine)
            ScratchcardWithCount(count)
        }

    for ((currentIndex, scratchcardWithCount) in allCards.withIndex()) {
        for (i in 1..scratchcardWithCount.matchesCount) {

            val cardIndex = currentIndex + i

            if (cardIndex < allCards.size) {
                allCards[cardIndex].count += scratchcardWithCount.count
            }
        }
    }

    return allCards.sumOf { it.count }
}
