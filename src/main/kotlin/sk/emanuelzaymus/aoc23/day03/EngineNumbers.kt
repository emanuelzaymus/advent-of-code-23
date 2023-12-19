package sk.emanuelzaymus.aoc23.day03

import sk.emanuelzaymus.aoc23.util.product
import java.io.File

/**
 * https://adventofcode.com/2023/day/3
 */
private fun main() {
    val input = File("data/day03.txt").readText()

    val sumOfNumbers = sumOfSymbolAdjacentNumbers(input)

    println("Problem 1: $sumOfNumbers") // 527446

    val sumOfGearRatios = sumOfGearRatios(input)

    println("Problem 2: $sumOfGearRatios") // 73201705
}

fun sumOfSymbolAdjacentNumbers(input: String): Int {
    return readEngine(input)
        .sumOfSymbolAdjacentNumbers()
}

fun sumOfGearRatios(input: String): Int {
    return readEngine(input)
        .sumOfGearRatios()
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

    fun sumOfGearRatios(): Int =
        getGearRatios()
            .sum()

    private fun getGearRatios(): Sequence<Int> = sequence {
        lines.forEachIndexed { x, row ->
            row.forEachIndexed { y, char ->

                if (isGear(char)) {
                    val adjacentNumbers = getAllAdjacentNumbers(x, y)

                    if (adjacentNumbers.size == 2) {
                        yield(adjacentNumbers.product())
                    }
                }
            }
        }
    }

    private fun isGear(char: Char): Boolean = char == '*'

    private fun getAllAdjacentNumbers(x: Int, y: Int): List<Int> = buildList {
        val yRange = y - 1..y + 1

        if (x > 0) {
            addAll(getOverlappingNumbers(numberWithIndices[x - 1], yRange))
        }

        addAll(getOverlappingNumbers(numberWithIndices[x], yRange))

        if (x < lines.lastIndex) {
            addAll(getOverlappingNumbers(numberWithIndices[x + 1], yRange))
        }
    }

    private fun getOverlappingNumbers(numbers: List<NumberWithIndices>, yRange: IntRange): List<Int> {
        return numbers
            .filter { it.yRange.any { y -> y in yRange } }
            .map { it.number }
    }

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
