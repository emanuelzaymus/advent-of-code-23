package sk.emanuelzaymus.aoc23.day16

import sk.emanuelzaymus.aoc23.day16.Direction.RIGHTWARD

typealias Contraption = Array<Array<Position>>

fun Contraption.restartAllEnergizedTiles() {
    forEach { row ->
        row.forEach(Position::restart)
    }
}

fun Contraption.energizeTilesFromTopLeftCorner() {
    energizeTilesFromPosition(0, 0, RIGHTWARD)
}

fun Contraption.energizeTilesFromPosition(x: Int, y: Int, startDirection: Direction) {
    check((x == 0 || x == lastIndex) || (y == 0 || y == this[x].lastIndex)) {
        "Position ($x, $y) is not on the edge of the contraption."
    }

    val startPosition = this[x][y]

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

    val nextDirections: Array<Direction> = nextPosition.tile
        .convertDirection(direction)

    nextDirections.forEach {
        energizeNextPosition(nextPosition, it)
    }
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
