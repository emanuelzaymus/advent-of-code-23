package sk.emanuelzaymus.aoc23.day06

import sk.emanuelzaymus.aoc23.util.product
import java.io.File

/**
 * https://adventofcode.com/2023/day/6
 */
private fun main() {
    val lines = File("data/day06.txt").readLines()

    val multiple = multiplyNumberOfWaysToWinTheRace(lines)

    println("Problem 1: $multiple") // 608902

    val singleRaceOptionCount = multiplyNumberOfWaysToWinTheRace(lines, singleRace = true)

    println("Problem 2: $singleRaceOptionCount") // 46173809
}

fun multiplyNumberOfWaysToWinTheRace(lines: List<String>, singleRace: Boolean = false): Int {
    return parseRaces(lines, singleRace)
        .map { timeAndDistance ->
            calculateNumberOfWaysToWinTheRace(timeAndDistance)
        }
        .product()
}

fun calculateNumberOfWaysToWinTheRace(race: Race): Int {
    return calculateWinningOptionsForRace(race)
        .count()
}

fun calculateWinningOptionsForRace(race: Race): List<WinningOption> {
    val (raceTime, recordDistance) = race

    fun calculateSpeed(buttonDownTime: Long) = buttonDownTime

    return (1..<raceTime)
        .map { buttonDownTime ->
            val speed = calculateSpeed(buttonDownTime)
            val timeToRace = raceTime - buttonDownTime

            val traveledDistance = speed * timeToRace
            WinningOption(buttonDownTime, traveledDistance)
        }
        .filter { (_, traveledDistance) ->
            traveledDistance > recordDistance
        }
}

data class Race(val raceTime: Long, val recordDistance: Long)

data class WinningOption(val buttonDownTime: Long, val traveledDistance: Long)

private fun parseRaces(lines: List<String>, singleRace: Boolean): List<Race> {
    val (raceTimes, recordDistances) =
        lines
            .map { line ->
                line.substringAfter(":")
                    .split(' ')
                    .filter { it.isNotBlank() }
                    .let { stringNumbers ->
                        if (singleRace) listOf(stringNumbers.joinToString(separator = ""))
                        else stringNumbers
                    }
                    .map { it.toLong() }
            }

    return raceTimes
        .zip(recordDistances)
        .map { (time, distance) -> Race(time, distance) }
}
