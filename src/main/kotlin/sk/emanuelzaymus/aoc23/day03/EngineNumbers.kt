package sk.emanuelzaymus.aoc23.day03

import java.io.File

/**
 * https://adventofcode.com/2023/day/2
 */
private fun main() {
    val input = File("data/day03.txt").readText()

    val sumOfNumbers = sumOfSymbolAdjacentNumbers(input)

    println("Problem 1: $sumOfNumbers") // 527446
}

fun sumOfSymbolAdjacentNumbers(input: String): Int {
    val engine = readEngine(input)

    return engine.sumOfSymbolAdjacentNumbers()
}

private fun readEngine(input: String): Engine {
    return Engine(
        input
            .lines()
            .filter { it.isNotBlank() }
    )
}

private class Engine(private val lines: List<String>) {

    private val numberWithIndices: List<List<NumberWithIndices>> = lines
        .mapIndexed { x, line ->
            buildList {
                var yFromIndex = 0
                while (yFromIndex < line.length) {
                    val char = line[yFromIndex]
                    if (!char.isDigit()) {
                        yFromIndex++
                        continue
                    }
                    var yToIndex = yFromIndex

                    while (yToIndex + 1 < line.length && line[yToIndex + 1].isDigit()) {
                        yToIndex++
                    }
                    val number = line.substring(yFromIndex..yToIndex).toInt()

                    this += NumberWithIndices(number, x, yFromIndex..yToIndex)
                    yFromIndex = yToIndex + 1
                }
            }
        }

    fun sumOfSymbolAdjacentNumbers(): Int =
        numberWithIndices
            .asSequence()
            .flatMap { it }
            .filter { it.isAdjacentToSymbol() }
            .sumOf { it.number }

    private fun NumberWithIndices.isAdjacentToSymbol(): Boolean {
        for (x in x - 1..x + 1) {
            for (y in yRange.first - 1..yRange.last + 1) {
                if (isSymbolSafe(x, y)) {
                    return true
                }
            }
        }
        return false
    }

    private fun isSymbolSafe(x: Int, y: Int): Boolean {
        if (x < 0 || x > lines.lastIndex) {
            return false
        }
        if (y < 0 || y > lines[x].lastIndex) {
            return false
        }
        return isSymbol(lines[x][y])
    }

    private fun isSymbol(char: Char): Boolean = char != '.' && !char.isDigit()

}

private data class NumberWithIndices(val number: Int, val x: Int, val yRange: IntRange)
