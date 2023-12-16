package sk.emanuelzaymus.aoc23.day16

import sk.emanuelzaymus.aoc23.day16.Direction.*
import sk.emanuelzaymus.aoc23.day16.Tile.*

typealias Contraption = Array<Array<Position>>

fun Contraption.energizeTiles() {
    val startPosition = this[0][0]
    val startDirection = RIGHTWARD

    energizeStartPosition(startPosition, startDirection)
}

private fun Contraption.energizeStartPosition(startPosition: Position, startDirection: Direction) {
    startPosition.isEnergized = true

    energizeNextPosition(startPosition, startDirection)
}

private val upward = arrayOf(UPWARD)
private val downward = arrayOf(DOWNWARD)
private val rightward = arrayOf(RIGHTWARD)
private val leftward = arrayOf(LEFTWARD)
private val upwardAndDownward = arrayOf(UPWARD, DOWNWARD)
private val rightwardAndLeftward = arrayOf(RIGHTWARD, LEFTWARD)

private fun Contraption.energizeNextPosition(position: Position, direction: Direction) {
    val nextPosition = getPositionInDirection(position, direction) ?: return

    nextPosition.isEnergized = true

    val nextDirections: Array<Direction> = when (nextPosition.tile) {
        // Tile .
        EMPTY -> when (direction) {
            UPWARD -> upward
            DOWNWARD -> downward
            RIGHTWARD -> rightward
            LEFTWARD -> leftward
        }
        // Tile /
        FORWARD_MIRROR -> when (direction) {
            UPWARD -> rightward
            DOWNWARD -> leftward
            RIGHTWARD -> upward
            LEFTWARD -> downward
        }
        // Tile \
        BACKWARD_MIRROR -> when (direction) {
            UPWARD -> leftward
            DOWNWARD -> rightward
            RIGHTWARD -> downward
            LEFTWARD -> upward
        }
        // Tile |
        VERTICAL_SPLITTER -> when (direction) {
            UPWARD -> upward
            DOWNWARD -> downward
            RIGHTWARD, LEFTWARD -> upwardAndDownward
        }
        // Tile -
        HORIZONTAL_SPLITTER -> when (direction) {
            UPWARD, DOWNWARD -> rightwardAndLeftward
            RIGHTWARD -> rightward
            LEFTWARD -> leftward
        }
    }

    nextDirections.forEach { energizeNextPosition(nextPosition, it) }
}

private fun Contraption.getPositionInDirection(position: Position, direction: Direction): Position? {
    val (newX, newY) = direction.shiftXY(position.x, position.y)

    if (newX !in indices || newY !in this[newX].indices) {
        return null
    }

    return this[newX][newY]
}

fun Contraption.getNumberOfEnergizedTiles(): Int =
    sumOf { row ->
        row.count { it.isEnergized }
    }
