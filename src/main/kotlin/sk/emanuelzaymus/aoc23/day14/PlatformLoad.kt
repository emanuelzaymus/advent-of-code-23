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

    val platformLoadAfterCycles = calculateTiltedPlatformLoad(platformInput, 1000_000_000)

    println("Problem 2: $platformLoadAfterCycles") // 106390
}

fun calculateTiltedNorthPlatformLoad(platformInput: String): Int {
    val platform = readPlatform(platformInput)

    platform.tiltPlatformInDirection(NORTH)

    return platform.calculatePlatformLoad()
}

fun calculateTiltedPlatformLoad(platformInput: String, cycles: Int): Int {
    val platform = readPlatform(platformInput)

    if (cycles < 10_000) {
        calculateUsingBruteForce(platform, cycles)
    }

    return calculateUsingStatistics(platform, cycles)
}

private fun calculateUsingBruteForce(platform: Platform, cycles: Int): Int {
    repeat(cycles) {
        platform.tiltWholeCycle()
    }

    return platform.calculatePlatformLoad()
}

private fun calculateUsingStatistics(
    platform: Platform,
    totalCycles: Int,
    warmUpCycles: Int = 10_000,
    statisticsCycles: Int = 100,
): Int {
    val statistics = collectStatisticsForCycles(platform, warmUpCycles, statisticsCycles)

    val period = findPeriod(statistics)

    val reminder = (totalCycles - warmUpCycles - 1) % period

    return statistics[reminder]
}

private fun collectStatisticsForCycles(platform: Platform, warmUpCycles: Int, statisticsCycles: Int): List<Int> {
    repeat(warmUpCycles) {
        platform.tiltWholeCycle()
    }

    return (0..<statisticsCycles)
        .map {
            platform.tiltWholeCycle()
            platform.calculatePlatformLoad()
        }
        .toList()
}

private fun findPeriod(statistics: List<Int>): Int {
    for ((numberIndex, number) in statistics.withIndex()) {
        val indexInSlice = statistics
            .slice(numberIndex + 1..<statistics.size)
            .indexOf(number)

        if (indexInSlice == -1) {
            continue
        }

        val realIndexOfNextOccurrence = numberIndex + 1 + indexInSlice

        val possiblePeriod = realIndexOfNextOccurrence - numberIndex

        if (isPeriod(statistics, possiblePeriod)) {
            return possiblePeriod
        }
    }
    error("Period not found.")
}

private fun isPeriod(statistics: List<Int>, possiblePeriod: Int): Boolean {
    check(statistics.size >= possiblePeriod * 2) { "Statistics size must be at least 2 times the possible period." }

    for ((index, number) in statistics.withIndex()) {
        if (index >= possiblePeriod) {
            break
        }

        var nextOccurrence = index + possiblePeriod

        while (nextOccurrence < statistics.size) {
            if (statistics[nextOccurrence] != number) {
                return false
            }

            nextOccurrence += possiblePeriod
        }
    }
    return true
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
