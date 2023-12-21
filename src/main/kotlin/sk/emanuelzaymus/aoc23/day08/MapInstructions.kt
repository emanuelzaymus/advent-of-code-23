package sk.emanuelzaymus.aoc23.day08

import java.io.File

/**
 * https://adventofcode.com/2023/day/8
 */
private fun main() {
    val input = File("data/day08.txt").readText()

    val stepCount = countStepsToReachEndDestination(input)

    println("Problem 1: $stepCount") // 12643
}

private const val LEFT_DIRECTION = 'L'
private const val RIGHT_DIRECTION = 'R'

private const val START_POSITION = "AAA"
private const val END_POSITION = "ZZZ"

fun countStepsToReachEndDestination(input: String): Int {
    val instructions = readInstructions(input)
    val destinationPairs = readDestinationPairs(input)

    var currentPosition = destinationPairs.first { it.position == START_POSITION }
    var stepCounter = 0

    while (currentPosition.position != END_POSITION) {
        val instruction = instructions[stepCounter % instructions.length]

        currentPosition =
            when (instruction) {
                LEFT_DIRECTION -> destinationPairs.first { it.position == currentPosition.leftDirection }
                RIGHT_DIRECTION -> destinationPairs.first { it.position == currentPosition.rightDirection }
                else -> error("Invalid instruction: $instruction")
            }

        stepCounter++
    }

    return stepCounter
}

private fun readInstructions(input: String): String = input.lineSequence().first()

private fun readDestinationPairs(input: String): List<DirectionPair> {
    return input
        .lineSequence()
        .drop(2)
        .filter { it.isNotBlank() }
        .map { line ->
            val (position, leftDirection, rightDirection) = line.split(" = (", ", ", ")")
            DirectionPair(position, leftDirection, rightDirection)
        }
        .toList()
}

private data class DirectionPair(val position: String, val leftDirection: String, val rightDirection: String)
