package sk.emanuelzaymus.aoc23.day10

import java.io.File

/**
 * https://adventofcode.com/2023/day/10
 */
private fun main() {
    val inputMaze = File("data/day10.txt").readText()

    val stepCount = findNumberOfStepsFromStartToFarthestPoint(inputMaze)

    println("Problem 1: $stepCount") // 6942

    val numberOfEnclosingTiles = findNumberOfEnclosedTiles(inputMaze)

    println("Problem 2: $numberOfEnclosingTiles") //
}

fun findNumberOfStepsFromStartToFarthestPoint(inputMaze: String): Int {
    val maze = readMaze(inputMaze)

    traverseMazeLoop(maze)

    val numberOfVisitedPositions = maze.getNumberOfVisitedPositions()

    println("Number of visited positions: $numberOfVisitedPositions")
    return numberOfVisitedPositions / 2
}

private fun traverseMazeLoop(maze: Maze) {
    val startPosition = maze.findStartPosition()
    var currentPosition = startPosition
    var lastPosition: Position? = null

    while (true) {
        val newPosition = maze.findNextPosition(currentPosition, lastPosition)
        newPosition.visited = true

        lastPosition = currentPosition
        currentPosition = newPosition

        if (currentPosition == startPosition) {
            break
        }
    }
}

fun findNumberOfEnclosedTiles(inputMaze: String): Int {
    val maze = readMaze(inputMaze)

    traverseMazeLoop(maze)

    return 0
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
