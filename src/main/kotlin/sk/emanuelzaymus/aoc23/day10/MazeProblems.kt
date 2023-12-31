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

    println("Problem 2: $numberOfEnclosingTiles") // 297
}

fun findNumberOfStepsFromStartToFarthestPoint(inputMaze: String): Int {
    val maze = readMaze(inputMaze)

    traverseMazeLoop(maze)

    val numberOfPathPositions = maze.getNumberOfPathPositions()

    println("Number of path positions: $numberOfPathPositions")
    return numberOfPathPositions / 2
}

fun findNumberOfEnclosedTiles(inputMaze: String): Int {
    val maze = readMaze(inputMaze)

    traverseMazeLoop(maze)

    replaceStartPosition(maze)

    val upscaledMaze = upscaleMaze(maze)

    val numberOfEnclosedTiles = upscaledMaze.findNumberOfEnclosedTilesThatAreNotNextToPathTiles()
    println("Number of enclosed tiles: $numberOfEnclosedTiles")

    return numberOfEnclosedTiles / 9
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
