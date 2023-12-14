package sk.emanuelzaymus.aoc23.day10

import sk.emanuelzaymus.aoc23.day10.Tile.*

fun upscaleMaze(maze: Maze): Maze {
    val upscaledMaze = Array(maze.size * 3) { i ->
        Array<Position?>(maze[i / 3].size * 3) { null }
    }

    maze.forEach { row ->
        row.forEach { position ->
            val upscaledX = position.x * 3
            val upscaledY = position.y * 3

            val upscaledTile: Array<String> = upscaleTile(position.tile)

            upscaledMaze[upscaledX][upscaledY] = pos(upscaledTile[0][0], upscaledX, upscaledY, position)
            upscaledMaze[upscaledX][upscaledY + 1] = pos(upscaledTile[0][1], upscaledX, upscaledY + 1, position)
            upscaledMaze[upscaledX][upscaledY + 2] = pos(upscaledTile[0][2], upscaledX, upscaledY + 2, position)

            upscaledMaze[upscaledX + 1][upscaledY] = pos(upscaledTile[1][0], upscaledX + 1, upscaledY, position)
            upscaledMaze[upscaledX + 1][upscaledY + 1] = pos(upscaledTile[1][1], upscaledX + 1, upscaledY + 1, position)
            upscaledMaze[upscaledX + 1][upscaledY + 2] = pos(upscaledTile[1][2], upscaledX + 1, upscaledY + 2, position)

            upscaledMaze[upscaledX + 2][upscaledY] = pos(upscaledTile[2][0], upscaledX + 2, upscaledY, position)
            upscaledMaze[upscaledX + 2][upscaledY + 1] = pos(upscaledTile[2][1], upscaledX + 2, upscaledY + 1, position)
            upscaledMaze[upscaledX + 2][upscaledY + 2] = pos(upscaledTile[2][2], upscaledX + 2, upscaledY + 2, position)
        }
    }

    return upscaledMaze
        .map { row ->
            row.map { position ->
                position ?: error("Upscaled position is null.")
            }.toTypedArray()
        }
        .toTypedArray()
}

private fun pos(tile: Char, upscaledX: Int, upscaledY: Int, originalPosition: Position): Position {
    return Position(tile, upscaledX, upscaledY, isPath = originalPosition.isPath && tile != '.')
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

    else -> error("Tile: $tile cannot be upscaled.")
}
