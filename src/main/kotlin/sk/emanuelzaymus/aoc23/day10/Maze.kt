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
        val nextPosition = getPositionTo(currentPosition, direction)

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

fun Maze.getPositionTo(fromPosition: Position, direction1: Direction, direction2: Direction? = null): Position? {
    val newX =
        if (direction2 != null) direction1.combinedShiftX(fromPosition.x, direction2)
        else direction1.shiftX(fromPosition.x)

    val newY =
        if (direction2 != null) direction1.combinedShiftY(fromPosition.y, direction2)
        else direction1.shiftY(fromPosition.y)

    if (!isPositionInside(newX, newY)) {
        return null
    }

    return this[newX][newY]
}

private fun Maze.isPositionInside(x: Int, y: Int): Boolean {
    return x in indices
        && y in 0..<this[x].size
}

fun Maze.getNumberOfPathPositions(): Int {
    return sumOf { row ->
        row.count { position ->
            position.isPath
        }
    }
}

fun Maze.asString() = joinToString("\n") { row ->
    row.joinToString("") { position ->
        position.tile.char.toString()
    }
}

fun Maze.findNumberOfEnclosedTilesThatAreNotNextToPathTiles(): Int {
    determinePositionsEnclosedInTheLoop()

    return sumOf { row ->
        row.count { position ->
            !position.isPath
                && position.isEnclosedInTheLoop == true
                && !isNextToPathTile(position)
        }
    }
}

fun Maze.determinePositionsEnclosedInTheLoop() {
    forEach { row ->
        row.asSequence()
            .filter { !it.isPath }
            .forEach { position ->
                resetVisitedPositions()
                position.isEnclosedInTheLoop = isEnclosedInTheLoop(this, position)
            }
    }
}

private fun Maze.resetVisitedPositions() {
    forEach { row ->
        row.forEach { position ->
            position.isVisited = false
        }
    }
}

private fun isEnclosedInTheLoop(maze: Maze, position: Position): Boolean {
    if (position.isPath) {
        error("Position $position is path.")
    }

    position.isVisited = true

    for (direction in Direction.valuesInOrder) {
        val neighbourPosition = maze
            .getPositionTo(position, direction)
            ?: return false

        if (neighbourPosition.isPath || neighbourPosition.isVisited) {
            continue
        }

        val isConnectedToPlaygroundBorder = !isEnclosedInTheLoop(maze, neighbourPosition)
        if (isConnectedToPlaygroundBorder) {
            return false
        }
    }

    return true
}

private fun Maze.isNextToPathTile(position: Position): Boolean {
    val allNeighbourPositions = sequence {
        yield(getPositionTo(position, Direction.NORTH))
        yield(getPositionTo(position, Direction.NORTH, Direction.EAST))
        yield(getPositionTo(position, Direction.EAST))
        yield(getPositionTo(position, Direction.SOUTH, Direction.EAST))
        yield(getPositionTo(position, Direction.SOUTH))
        yield(getPositionTo(position, Direction.SOUTH, Direction.WEST))
        yield(getPositionTo(position, Direction.WEST))
        yield(getPositionTo(position, Direction.NORTH, Direction.WEST))
    }
    return allNeighbourPositions
        .filterNotNull()
        .any { it.isPath }
}
