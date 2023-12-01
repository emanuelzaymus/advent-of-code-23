package sk.emanuelzaymus.aoc23.day1

import java.io.File

/**
 * https://adventofcode.com/2023/day/1
 */
fun main() {
    val lines = File("data/day1.txt").readLines()

    val sum = sumOfDecryptedValues(lines)

    println(sum) // 55971
}

fun sumOfDecryptedValues(values: List<String>): Int {
    return values
        .map {
            it.first { c -> c.isDigit() } to it.last { c -> c.isDigit() }
        }
        .sumOf { (firstDigit, lastDigit) ->
            (firstDigit.digitToInt() * 10) + lastDigit.digitToInt()
        }
}
