package sk.emanuelzaymus.aoc23.day1

import java.io.File

val digitNumberMap = mapOf(
    "1" to 1, "2" to 2, "3" to 3, "4" to 4, "5" to 5, "6" to 6, "7" to 7, "8" to 8, "9" to 9,
)

private val wordNumberMap = mapOf(
    "one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9,
)

val digitAndWordNumberMap = digitNumberMap + wordNumberMap

/** https://adventofcode.com/2023/day/1 */
private fun main() {
    val lines = File("data/day1.txt").readLines()

    val sum = sumOfDecryptedValues(lines, digitNumberMap)
    println("Problem 1: $sum") // 55971

    val sumWithWords = sumOfDecryptedValues(lines, digitAndWordNumberMap)
    println("Problem 2: $sumWithWords") // 54719
}

fun sumOfDecryptedValues(lines: List<String>, numberMap: Map<String, Int>): Int {
    return lines.sumOf {
        it.findFirstNumber(numberMap) * 10 + it.findLastNumber(numberMap)
    }
}

private fun String.findFirstNumber(numberMap: Map<String, Int>): Int {
    return numberMap
        .entries
        .minOfWith(
            compareBy {
                val index = indexOf(it.key)
                if (index == -1) length else index
            }
        ) {
            it
        }
        .value
}

private fun String.findLastNumber(numberMap: Map<String, Int>): Int {
    return numberMap
        .entries
        .maxOfWith(
            compareBy { lastIndexOf(it.key) }
        ) {
            it
        }
        .value
}

/** First solution of Problem 1 */
fun simpleSumOfDecryptedValues(values: List<String>): Int {
    return values
        .map {
            it.first { c -> c.isDigit() } to it.last { c -> c.isDigit() }
        }
        .sumOf { (firstDigit, lastDigit) ->
            (firstDigit.digitToInt() * 10) + lastDigit.digitToInt()
        }
}
