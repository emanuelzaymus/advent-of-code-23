package sk.emanuelzaymus.aoc23.day16

import sk.emanuelzaymus.aoc23.day16.Direction.*
import java.io.File

/**
 * https://adventofcode.com/2023/day/16
 */
private fun main() {
    val input = File("data/day16.txt").readText()

    val energizedTiles = numberOfEnergizedTiles(input)

    println("Problem 1: $energizedTiles") // 7236

    val mostEnergizedTiles = numberOfEnergizedTilesWithBestConfiguration(input)

    println("Problem 2: $mostEnergizedTiles") // 7521
}

fun numberOfEnergizedTiles(contraptionInput: String): Int {
    val contraption = readContraption(contraptionInput)

    contraption.energizeTilesFromTopLeftCorner()

    return contraption.getNumberOfEnergizedTiles()
}

fun numberOfEnergizedTilesWithBestConfiguration(contraptionInput: String): Int {
    val contraption = readContraption(contraptionInput)

    val maxX = contraption.lastIndex
    val maxY = contraption[0].lastIndex

    return maxOf(
        getMaxEnergizedTilesFromConfigurations(contraption, 0..0, 0..maxY, DOWNWARD),
        getMaxEnergizedTilesFromConfigurations(contraption, maxX..maxX, 0..maxY, UPWARD),
        getMaxEnergizedTilesFromConfigurations(contraption, 0..maxX, 0..0, RIGHTWARD),
        getMaxEnergizedTilesFromConfigurations(contraption, 0..maxX, maxY..maxY, LEFTWARD),
    )
}

fun getMaxEnergizedTilesFromConfigurations(
    contraption: Contraption,
    xRange: IntRange,
    yRange: IntRange,
    direction: Direction
): Int {
    var maxEnergizedTiles = 0

    for (x in xRange) for (y in yRange) {
        contraption.restartAllEnergizedTiles()

        contraption.energizeTilesFromPosition(x, y, direction)

        val energizedTiles = contraption.getNumberOfEnergizedTiles()

        if (energizedTiles > maxEnergizedTiles) {
            maxEnergizedTiles = energizedTiles
        }
    }

    return maxEnergizedTiles
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
