package sk.emanuelzaymus.aoc23.day10

import sk.emanuelzaymus.aoc23.day10.Tile.*
import java.io.File

private typealias Maze = Array<Array<Position>>

private fun main() {
    val inputMaze = File("data/day10.txt").readText()

    val stepCount = findNumberOfStepsFromStartToFarthestPoint(inputMaze)

    println("Problem 1: $stepCount") // 6942

    val numberOfEnclosingTiles = findNumberOfEnclosedTiles(inputMaze)

    println("Problem 2: $numberOfEnclosingTiles") //
}

fun findNumberOfEnclosedTiles(inputMaze: String): Int {
    TODO("Not yet implemented")
}

fun findNumberOfStepsFromStartToFarthestPoint(inputMaze: String): Int {
    val maze = readMaze(inputMaze)

    val startPosition = findStartPosition(maze)
    var currentPosition = startPosition
    var lastPosition: Position? = null
    var stepCount = 0

    while (true) {
        val newPosition = findNextPosition(maze, currentPosition, lastPosition)
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

private fun findNextPosition(maze: Maze, currentPosition: Position, lastPosition: Position?): Position {
    for (direction in Direction.entries) {
        val nextPosition = maze.getPositionTo(direction, currentPosition)

        if (
            nextPosition != null
            && currentPosition.isConnectingTo(nextPosition)
            && nextPosition != lastPosition
        ) {
            return nextPosition
        }
    }

    throw IllegalStateException("No next position found from current position: $currentPosition.")
}

private fun Maze.getPositionTo(direction: Direction, fromPosition: Position): Position? {
    val newX = direction.shiftX(fromPosition.x)
    val newY = direction.shiftY(fromPosition.y)

    if (!isPositionInside(newX, newY)) {
        return null
    }

    return this[newX][newY]
}

private fun Maze.isPositionInside(x: Int, y: Int): Boolean {
    return x in indices
        && y in 0..<this[x].size
}

private fun findStartPosition(maze: Maze): Position {
    maze.forEach { row ->
        row.forEach { position ->
            if (position.tile == START) {
                return position
            }
        }
    }

    throw IllegalStateException("Maze does not contain start position.")
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
