package sk.emanuelzaymus.aoc23.day10

import sk.emanuelzaymus.aoc23.day10.Tile.*

data class Position(val tile: Tile, val x: Int, val y: Int) {

    constructor(tile: Char, x: Int, y: Int) : this(Tile.of(tile), x, y)

    fun isConnectingTo(other: Position): Boolean {
        val t = other.tile
        if (this.tile == NONE || t == NONE) {
            return false
        }
        return when {
            isToDirection(Direction.NORTH, other) -> when (tile) {
                START, VERTICAL, NORTH_EAST, NORTH_WEST -> t == START || t == VERTICAL || t == SOUTH_WEST || t == SOUTH_EAST
                else -> false
            }

            isToDirection(Direction.SOUTH, other) -> when (tile) {
                START, VERTICAL, SOUTH_WEST, SOUTH_EAST -> t == START || t == VERTICAL || t == NORTH_EAST || t == NORTH_WEST
                else -> false
            }

            isToDirection(Direction.EAST, other) -> when (tile) {
                START, HORIZONTAL, NORTH_EAST, SOUTH_EAST -> t == START || t == HORIZONTAL || t == SOUTH_WEST || t == NORTH_WEST
                else -> false
            }

            isToDirection(Direction.WEST, other) -> when (tile) {
                START, HORIZONTAL, NORTH_WEST, SOUTH_WEST -> t == START || t == HORIZONTAL || t == SOUTH_EAST || t == NORTH_EAST
                else -> false
            }

            else -> error("Point $other is not connectable to this $this.")
        }
    }

    private fun isToDirection(direction: Direction, other: Position): Boolean {
        return direction.shiftX(x) == other.x
            && direction.shiftY(y) == other.y
    }

}
