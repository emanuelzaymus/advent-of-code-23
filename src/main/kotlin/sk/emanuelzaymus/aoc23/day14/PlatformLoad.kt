package sk.emanuelzaymus.aoc23.day14

import sk.emanuelzaymus.aoc23.day14.Direction.NORTH
import java.io.File

/**
 * https://adventofcode.com/2023/day/14
 */
private fun main() {
    val platformInput = File("data/day14.txt").readText()

    val platformLoad = calculateTiltedNorthPlatformLoad(platformInput)

    println("Problem 1: $platformLoad") // 106186

    val platformLoadAfterCycles = calculateTiltedPlatformLoad(platformInput, 10_000)

    println("Problem 2: $platformLoadAfterCycles") // 106350 is too low !!!
}

fun calculateTiltedNorthPlatformLoad(platformInput: String): Int {
    val platform = readPlatform(platformInput)

    platform.tiltPlatformInDirection(NORTH)

    return platform.calculatePlatformLoad()
}

fun calculateTiltedPlatformLoad(platformInput: String, cycles: Int): Int {
    val platform = readPlatform(platformInput)

    repeat(cycles) {
        platform.tiltWholeCycle()
    }

    return platform.calculatePlatformLoad()
}

fun readPlatform(platformInput: String): Platform {
    return platformInput
        .lines()
        .filter { it.isNotBlank() }
        .map { row ->
            row
                .map { createPosition(it) }
                .toTypedArray()
        }
        .toTypedArray()
}
