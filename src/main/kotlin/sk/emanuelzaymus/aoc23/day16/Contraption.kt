package sk.emanuelzaymus.aoc23.day16

import sk.emanuelzaymus.aoc23.day16.Direction.*
import sk.emanuelzaymus.aoc23.day16.Tile.*

typealias Contraption = Array<Array<Position>>

private val upward = arrayOf(UPWARD)
private val downward = arrayOf(DOWNWARD)
private val rightward = arrayOf(RIGHTWARD)
private val leftward = arrayOf(LEFTWARD)
private val upwardAndDownward = arrayOf(UPWARD, DOWNWARD)
private val rightwardAndLeftward = arrayOf(RIGHTWARD, LEFTWARD)

fun Contraption.energizeTiles() {
    val startPosition = this[0][0]
    val startDirection = RIGHTWARD

    energizeNextPosition(startPosition, startDirection, isStartPosition = true)
}

private fun Contraption.energizeNextPosition(
    position: Position,
    direction: Direction,
    isStartPosition: Boolean = false
) {
    val nextPosition =
        if (isStartPosition) position
        else getPositionInDirection(position, direction) ?: return

    if (direction in nextPosition.movedInDirections) {
        return
    }

    nextPosition.isEnergized = true
    nextPosition.movedInDirections += direction

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
