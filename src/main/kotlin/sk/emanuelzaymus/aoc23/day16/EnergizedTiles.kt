package sk.emanuelzaymus.aoc23.day16

import java.io.File

/**
 * https://adventofcode.com/2023/day/16
 */
private fun main() {
    val input = File("data/day16.txt").readText()

    val energizedTiles = numberOfEnergizedTiles(input)

    println("Problem 1: $energizedTiles") //
}

fun numberOfEnergizedTiles(contraptionInput: String): Int {
    val contraption = readContraption(contraptionInput)

    contraption.energizeTiles()

    return contraption.getNumberOfEnergizedTiles()
}

fun readContraption(contraptionInput: String): Contraption {
    return contraptionInput
        .trim()
        .lines()
        .mapIndexed { x, row ->
            row
                .mapIndexed { y, char ->
                    Position(char, x, y)
                }
                .toTypedArray()
        }
        .toTypedArray()
}
