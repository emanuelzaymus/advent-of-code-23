package sk.emanuelzaymus.aoc23.day10

import sk.emanuelzaymus.aoc23.day10.Tile.*

fun upscaleMaze(maze: Maze): Maze {
    val scaledMaze = Array(maze.size * 3) { i ->
        Array<Position?>(maze[i / 3].size * 3) { null }
    }

    maze.forEach { row ->
        row.forEach { position ->
            val scaledX = position.x * 3
            val scaledY = position.y * 3

            val scaledTile: Array<String> = upscaleTile(position.tile)

            scaledMaze[scaledX][scaledY] = Position(scaledTile[0][0], scaledX, scaledY)
            scaledMaze[scaledX][scaledY + 1] = Position(scaledTile[0][1], scaledX, scaledY + 1)
            scaledMaze[scaledX][scaledY + 2] = Position(scaledTile[0][2], scaledX, scaledY + 2)

            scaledMaze[scaledX + 1][scaledY] = Position(scaledTile[1][0], scaledX + 1, scaledY)
            scaledMaze[scaledX + 1][scaledY + 1] = Position(scaledTile[1][1], scaledX + 1, scaledY + 1)
            scaledMaze[scaledX + 1][scaledY + 2] = Position(scaledTile[1][2], scaledX + 1, scaledY + 2)

            scaledMaze[scaledX + 2][scaledY] = Position(scaledTile[2][0], scaledX + 2, scaledY)
            scaledMaze[scaledX + 2][scaledY + 1] = Position(scaledTile[2][1], scaledX + 2, scaledY + 1)
            scaledMaze[scaledX + 2][scaledY + 2] = Position(scaledTile[2][2], scaledX + 2, scaledY + 2)
        }
    }

    return scaledMaze
        .map { row ->
            row.map { position ->
                position ?: error("Upscaled position is null.")
            }.toTypedArray()
        }
        .toTypedArray()
}

private fun upscaleTile(tile: Tile): Array<String> = when (tile) {
    VERTICAL -> arrayOf(
        ".|.",
        ".|.",
        ".|.",
    )

    HORIZONTAL -> arrayOf(
        "...",
        "---",
        "...",
    )

    NORTH_EAST -> arrayOf(
        ".|.",
        ".L-",
        "...",
    )

    NORTH_WEST -> arrayOf(
        ".|.",
        "-J.",
        "...",
    )

    SOUTH_WEST -> arrayOf(
        "...",
        "-7.",
        ".|.",
    )

    SOUTH_EAST -> arrayOf(
        "...",
        ".F-",
        ".|.",
    )

    NONE -> arrayOf(
        "...",
        "...",
        "...",
    )

    else -> error("Tile: $tile cannot be scaled.")
}
