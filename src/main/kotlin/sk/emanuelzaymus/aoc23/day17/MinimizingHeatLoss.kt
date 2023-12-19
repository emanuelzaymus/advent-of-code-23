package sk.emanuelzaymus.aoc23.day17

import java.io.File

/**
 * https://adventofcode.com/2023/day/17
 */
private fun main() {
    val cityMapInput = File("data/day17.txt").readText()

    val minimalHeatLoss = minimizeHeatLoss(cityMapInput)

    println("Problem 1: $minimalHeatLoss") //
}

fun minimizeHeatLoss(cityMapInput: String): Int {
    val cityMap = readCityMap(cityMapInput)

    cityMap.runDijkstra()

    return 0
}

fun readCityMap(cityMapInput: String): CityMap {
    return cityMapInput
        .lines()
        .mapIndexed { y, row ->
            row
                .mapIndexed { x, char ->
                    CityBlock(char.digitToInt(), x, y)
                }
                .toTypedArray()
        }
        .toTypedArray()
}
