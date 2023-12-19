package sk.emanuelzaymus.aoc23.day03

import java.io.File

/**
 * https://adventofcode.com/2023/day/2
 */
private fun main() {
    val input = File("data/day03.txt").readText()

    val sumOfNumbers = sumOfSymbolAdjacentNumbers(input)

    println("Problem 1: $sumOfNumbers") //
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

                    while (line.length > yToIndex + 1 && line[yToIndex + 1].isDigit()) {
                        yToIndex++
                    }
                    val number = line.substring(yFromIndex..yToIndex).toInt()

                    add(NumberWithIndices(number, x, yFromIndex..yToIndex))
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
        val yFrom = if (yRange.first > 0) yRange.first - 1 else yRange.first
        val yTo = if (yRange.last < lines[x].lastIndex) yRange.last + 1 else yRange.last

        if (x > 0) {
            val top = lines[x - 1].substring(yFrom..yTo)

            if (top.any(::isSymbol)) {
                return true
            }
        }

        if (isSymbol(lines[x][yFrom]) || isSymbol(lines[x][yTo])) {
            return true
        }

        if (x < lines.lastIndex) {
            val bottom = lines[x + 1].substring(yFrom..yTo)

            if (bottom.any(::isSymbol)) {
                return true
            }
        }

        return false
    }

    private fun isSymbol(char: Char): Boolean = char != '.' && !char.isDigit()

    private fun isSymbolSafe(x: Int, y: Int): Boolean {
        if (x < 0 || x > lines.lastIndex) {
            return false
        }
        if (y < 0 || y > lines[x].lastIndex) {
            return false
        }
        return isSymbol(lines[x][y])
    }

}

private data class NumberWithIndices(val number: Int, val x: Int, val yRange: IntRange)
