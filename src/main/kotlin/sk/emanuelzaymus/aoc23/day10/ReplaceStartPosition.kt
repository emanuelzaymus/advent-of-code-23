package sk.emanuelzaymus.aoc23.day10

fun replaceStartPosition(maze: Maze) {
    val startPosition = maze.findStartPosition()

    val toNorth = maze.getPositionTo(startPosition, Direction.NORTH)
    val toSouth = maze.getPositionTo(startPosition, Direction.SOUTH)
    val toEast = maze.getPositionTo(startPosition, Direction.EAST)
    val toWest = maze.getPositionTo(startPosition, Direction.WEST)

    val isConnectableToNorth = toNorth != null && startPosition.isConnectingTo(toNorth)
    val isConnectableToSouth = toSouth != null && startPosition.isConnectingTo(toSouth)
    val isConnectableToEast = toEast != null && startPosition.isConnectingTo(toEast)
    val isConnectableToWest = toWest != null && startPosition.isConnectingTo(toWest)

    val startReplacingPosition =
        with(startPosition) {
            when {
                isConnectableToNorth && isConnectableToSouth -> copy(tile = Tile.VERTICAL)
                isConnectableToEast && isConnectableToWest -> copy(tile = Tile.HORIZONTAL)
                isConnectableToNorth && isConnectableToEast -> copy(tile = Tile.NORTH_EAST)
                isConnectableToNorth && isConnectableToWest -> copy(tile = Tile.NORTH_WEST)
                isConnectableToSouth && isConnectableToEast -> copy(tile = Tile.SOUTH_EAST)
                isConnectableToSouth && isConnectableToWest -> copy(tile = Tile.SOUTH_WEST)
                else -> error("Start position $startPosition is not connectable to any other positions.")
            }
        }

    maze[startPosition.x][startPosition.y] = startReplacingPosition
}
