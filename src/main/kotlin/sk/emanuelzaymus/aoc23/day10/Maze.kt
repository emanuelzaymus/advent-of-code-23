package sk.emanuelzaymus.aoc23.day10

import sk.emanuelzaymus.aoc23.day10.Tile.*
import java.io.File

private typealias Maze = Array<CharArray>

private fun main() {
    val stepCount = findNumberOfStepsFromStartToFarthestPoint(File("data/day10.txt").readText())

    println("Problem 1: $stepCount") // 6942
}

fun findNumberOfStepsFromStartToFarthestPoint(inputMaze: String): Int {
    val maze = readMaze(inputMaze)

    val startPosition = findStartPosition(maze)
    var currentPosition = startPosition
    var lastPosition: Position? = null
    var stepCount = 0

    while (true) {
        findNextPosition(maze, currentPosition, lastPosition)
            .also { newPosition ->
                lastPosition = currentPosition
                currentPosition = newPosition
            }

        stepCount++
        println("New step count: $stepCount")
        println("Current position: $currentPosition")

        if (currentPosition == startPosition) {
            break
        }
    }

    println("Step count: $stepCount")
    return stepCount / 2
}

private fun findNextPosition(maze: Maze, currentPosition: Position, lastPosition: Position?): Position {
    val northPosition = maze.getPositionToNorth(currentPosition)
    if (northPosition != null && currentPosition.isConnectingTo(northPosition) && northPosition != lastPosition) {
        return northPosition
    }

    val southPosition = maze.getPositionToSouth(currentPosition)
    if (southPosition != null && currentPosition.isConnectingTo(southPosition) && southPosition != lastPosition) {
        return southPosition
    }

    val eastPosition = maze.getPositionToEast(currentPosition)
    if (eastPosition != null && currentPosition.isConnectingTo(eastPosition) && eastPosition != lastPosition) {
        return eastPosition
    }

    val westPosition = maze.getPositionToWest(currentPosition)
    if (westPosition != null && currentPosition.isConnectingTo(westPosition) && westPosition != lastPosition) {
        return westPosition
    }

    throw IllegalStateException("No next position found from current position: $currentPosition.")
}

private fun Maze.getPositionToNorth(fromPosition: Position): Position? {
    val (_, x, y) = fromPosition
    if (x - 1 < 0) {
        return null
    }
    return Position(this[x - 1][y], x - 1, y)
}

private fun Maze.getPositionToSouth(fromPosition: Position): Position? {
    val (_, x, y) = fromPosition
    if (x + 1 >= size) {
        return null
    }
    return Position(this[x + 1][y], x + 1, y)
}

private fun Maze.getPositionToEast(fromPosition: Position): Position? {
    val (_, x, y) = fromPosition
    if (y + 1 >= this[x].size) {
        return null
    }
    return Position(this[x][y + 1], x, y + 1)
}

private fun Maze.getPositionToWest(fromPosition: Position): Position? {
    val (_, x, y) = fromPosition
    if (y - 1 < 0) {
        return null
    }
    return Position(this[x][y - 1], x, y - 1)
}

private fun findStartPosition(maze: Maze): Position {
    maze.forEachIndexed { x, line ->
        line.forEachIndexed { y, tile ->
            if (tile == START.char) {
                return Position(START, x, y)
            }
        }
    }
    throw IllegalStateException("Maze does not contain start position.")
}

private fun readMaze(inputMaze: String): Maze {
    return inputMaze
        .lines()
        .filterNot { it.isBlank() }
        .map { it.toCharArray() }
        .toTypedArray()
}
