package sk.emanuelzaymus.aoc23.day10

typealias Maze = Array<Array<Position>>

fun Maze.findStartPosition(): Position {
    forEach { row ->
        row.forEach { position ->
            if (position.tile == Tile.START) {
                return position
            }
        }
    }

    throw IllegalStateException("Maze does not contain start position.")
}

fun Maze.findNextPosition(currentPosition: Position, lastPosition: Position?): Position {
    for (direction in Direction.entries) {
        val nextPosition = getPositionTo(direction, currentPosition)

        if (
            nextPosition != null
            && currentPosition.isConnectingTo(nextPosition)
            && nextPosition != lastPosition
        ) {
            return nextPosition
        }
    }

    error("No next position found from current position: $currentPosition.")
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

fun Maze.getNumberOfVisitedPositions(): Int {
    return sumOf { row ->
        row.count { position ->
            position.visited
        }
    }
}
