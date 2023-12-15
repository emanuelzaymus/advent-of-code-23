package sk.emanuelzaymus.aoc23.day15

import java.io.File

/**
 * https://adventofcode.com/2023/day/15
 */
private fun main() {
    val input = File("data/day15.txt")
        .readText()
        .trim()

    val hash = hashSequence(input)

    println("Problem 1: $hash") // 511343
}

fun hashSequence(inputSequence: String): Int {
    return inputSequence
        .splitToSequence(",")
        .sumOf { hash(it) }
}

fun hash(string: String): Int {
    var currentValue = 0

    for (c in string) {
        currentValue += c.code
        currentValue *= 17
        currentValue %= 256
    }

    return currentValue
}
