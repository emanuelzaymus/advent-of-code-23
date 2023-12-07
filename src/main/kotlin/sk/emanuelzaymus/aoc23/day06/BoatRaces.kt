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
}

fun multiplyNumberOfWaysToWinTheRace(lines: List<String>): Int {
    return parseRaces(lines)
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

    fun calculateSpeed(buttonDownTime: Int): Int = buttonDownTime

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

data class Race(val raceTime: Int, val recordDistance: Int)

data class WinningOption(val buttonDownTime: Int, val traveledDistance: Int)

private fun parseRaces(lines: List<String>): List<Race> {
    val (raceTimes, recordDistances) =
        lines
            .map { line ->
                line.substringAfter(":")
                    .split(' ')
                    .filter { it.isNotBlank() }
                    .map { it.toInt() }
            }

    return raceTimes
        .zip(recordDistances)
        .map { (time, distance) -> Race(time, distance) }
}
