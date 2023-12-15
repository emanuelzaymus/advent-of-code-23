package sk.emanuelzaymus.aoc23.day14

import java.io.File

/**
 * https://adventofcode.com/2023/day/14
 */
private fun main() {
    val platformInput = File("data/day14.txt").readText()

    val platformLoad = calculateTiltedPlatformLoad(platformInput)

    println("Problem 1: $platformLoad") // 106186
}

fun calculateTiltedPlatformLoad(platformInput: String): Int {
    val platform = readPlatform(platformInput)

    platform.tiltPlatformNorth()

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
