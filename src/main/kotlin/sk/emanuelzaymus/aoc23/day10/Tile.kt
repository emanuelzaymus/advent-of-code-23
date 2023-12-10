package sk.emanuelzaymus.aoc23.day10

enum class Tile(val char: Char) {
    VERTICAL('|'),
    HORIZONTAL('-'),
    NORTH_EAST('L'),
    NORTH_WEST('J'),
    SOUTH_WEST('7'),
    SOUTH_EAST('F'),
    NONE('.'),
    START('S');

    companion object {
        fun of(char: Char): Tile = entries.single { it.char == char }
    }

}
