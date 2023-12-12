package sk.emanuelzaymus.aoc23.day10

import sk.emanuelzaymus.aoc23.day10.Tile.*
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

    replaceStartPositions(maze)

    val scaledMaze = upscaleMaze(maze)

    return 0
}

private fun replaceStartPositions(maze: Maze) {
    val startPosition = maze.findStartPosition()

    val toNorth = maze.getPositionTo(Direction.NORTH, startPosition)
    val toSouth = maze.getPositionTo(Direction.SOUTH, startPosition)
    val toEast = maze.getPositionTo(Direction.EAST, startPosition)
    val toWest = maze.getPositionTo(Direction.WEST, startPosition)

    val isConnectableToNorth = toNorth != null && startPosition.isConnectingTo(toNorth)
    val isConnectableToSouth = toSouth != null && startPosition.isConnectingTo(toSouth)
    val isConnectableToEast = toEast != null && startPosition.isConnectingTo(toEast)
    val isConnectableToWest = toWest != null && startPosition.isConnectingTo(toWest)

    val startReplacingPosition =
        with(startPosition) {
            when {
                isConnectableToNorth && isConnectableToSouth -> copy(tile = VERTICAL)
                isConnectableToEast && isConnectableToWest -> copy(tile = HORIZONTAL)
                isConnectableToNorth && isConnectableToEast -> copy(tile = NORTH_EAST)
                isConnectableToNorth && isConnectableToWest -> copy(tile = NORTH_WEST)
                isConnectableToSouth && isConnectableToEast -> copy(tile = SOUTH_EAST)
                isConnectableToSouth && isConnectableToWest -> copy(tile = SOUTH_WEST)
                else -> error("Start position $startPosition is not connectable to any other positions.")
            }
        }

    maze[startPosition.x][startPosition.y] = startReplacingPosition
}

fun readMaze(inputMaze: String): Maze {
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
