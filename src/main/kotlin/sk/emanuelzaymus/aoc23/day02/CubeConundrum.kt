package sk.emanuelzaymus.aoc23.day02

import java.io.File

/**
 * https://adventofcode.com/2023/day/2
 */
private fun main() {
    val lines = File("data/day02.txt").readLines()

    val gameIdsSum = calculatePossibleGameIdsSum(lines, maxRedCubes = 12, macGreenCubes = 13, macBlueCubes = 14)

    println("Problem 1: $gameIdsSum") // 2377
}

fun calculatePossibleGameIdsSum(lines: List<String>, maxRedCubes: Int, macGreenCubes: Int, macBlueCubes: Int): Int {
    return lines
        .map { Game.parse(it) }
        .filter { game ->
            game
                .cubeSets
                .all { cubeSet ->
                    cubeSet.redCubes <= maxRedCubes &&
                        cubeSet.greenCubes <= macGreenCubes &&
                        cubeSet.blueCubes <= macBlueCubes
                }
        }
        .sumOf { it.gameNumber }
}

data class Game(val gameNumber: Int, val cubeSets: List<CubeSet>) {
    companion object {
        fun parse(line: String) = Game(parseGameNumber(line), parseCubeSets(line))

        private fun parseGameNumber(line: String): Int =
            line.substringAfter("Game ")
                .substringBefore(':')
                .toInt()

        private fun parseCubeSets(line: String): List<CubeSet> =
            line.substringAfter(": ")
                .split("; ")
                .map { CubeSet.parse(it) }
    }
}

data class CubeSet(val redCubes: Int, val greenCubes: Int, val blueCubes: Int) {
    companion object {
        fun parse(cubeSetLine: String): CubeSet {
            var reds = 0
            var greens = 0
            var blues = 0

            cubeSetLine
                .split(", ")
                .forEach { record ->
                    val (number, color) = record.split(' ')
                    when (color) {
                        "red" -> reds = number.toInt()
                        "green" -> greens = number.toInt()
                        "blue" -> blues = number.toInt()
                    }
                }

            return CubeSet(reds, greens, blues)
        }
    }
}
