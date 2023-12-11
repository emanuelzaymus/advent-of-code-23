package sk.emanuelzaymus.aoc23.day10

import java.io.File

private fun main() {
    val inputMaze = File("data/day10.txt").readText()

    val stepCount = findNumberOfStepsFromStartToFarthestPoint(inputMaze)

    println("Problem 1: $stepCount") // 6942

    val numberOfEnclosingTiles = findNumberOfEnclosedTiles(inputMaze)

    println("Problem 2: $numberOfEnclosingTiles") //
}

fun findNumberOfStepsFromStartToFarthestPoint(inputMaze: String): Int {
    val maze = readMaze(inputMaze)

    val startPosition = maze.findStartPosition()
    var currentPosition = startPosition
    var lastPosition: Position? = null
    var stepCount = 0

    while (true) {
        val newPosition = maze.findNextPosition(currentPosition, lastPosition)
        lastPosition = currentPosition
        currentPosition = newPosition

        stepCount++

        if (currentPosition == startPosition) {
            break
        }
    }

    println("Step count: $stepCount")
    return stepCount / 2
}

fun findNumberOfEnclosedTiles(inputMaze: String): Int {
    TODO("Not yet implemented")
}

private fun readMaze(inputMaze: String): Maze {
    return inputMaze
        .lines()
        .filter { it.isNotBlank() }
        .mapIndexed { x, row ->
            row
                .mapIndexed { y, char ->
                    Position(char, x, y)
                }
                .toTypedArray()
        }
        .toTypedArray()
}
