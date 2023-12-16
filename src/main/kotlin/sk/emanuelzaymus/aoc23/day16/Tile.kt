package sk.emanuelzaymus.aoc23.day16

enum class Tile(val char: Char) {

    EMPTY('.'),
    FORWARD_MIRROR('/'),
    BACKWARD_MIRROR('\\'),
    VERTICAL_SPLITTER('|'),
    HORIZONTAL_SPLITTER('-');

    companion object {
        fun of(char: Char): Tile = entries.first { it.char == char }
    }

}
