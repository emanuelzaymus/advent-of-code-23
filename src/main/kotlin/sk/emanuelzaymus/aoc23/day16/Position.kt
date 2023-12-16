package sk.emanuelzaymus.aoc23.day16

data class Position(
    val tile: Tile,
    val x: Int,
    val y: Int,
    var isEnergized: Boolean = false,
    val movedInDirections: MutableSet<Direction> = mutableSetOf()
) {
    constructor(char: Char, x: Int, y: Int) : this(Tile.of(char), x, y)
}
