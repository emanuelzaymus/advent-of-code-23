package sk.emanuelzaymus.aoc23.day16

import sk.emanuelzaymus.aoc23.day16.Direction.*

private val upward = arrayOf(UPWARD)
private val downward = arrayOf(DOWNWARD)
private val rightward = arrayOf(RIGHTWARD)
private val leftward = arrayOf(LEFTWARD)
private val upwardAndDownward = arrayOf(UPWARD, DOWNWARD)
private val rightwardAndLeftward = arrayOf(RIGHTWARD, LEFTWARD)

enum class Tile(val char: Char, val convertDirection: (Direction) -> Array<Direction>) {

    EMPTY('.', { direction ->
        when (direction) {
            UPWARD -> upward
            DOWNWARD -> downward
            RIGHTWARD -> rightward
            LEFTWARD -> leftward
        }
    }),

    FORWARD_MIRROR('/', { direction ->
        when (direction) {
            UPWARD -> rightward
            DOWNWARD -> leftward
            RIGHTWARD -> upward
            LEFTWARD -> downward
        }
    }),

    BACKWARD_MIRROR('\\', { direction ->
        when (direction) {
            UPWARD -> leftward
            DOWNWARD -> rightward
            RIGHTWARD -> downward
            LEFTWARD -> upward
        }
    }),

    VERTICAL_SPLITTER('|', { direction ->
        when (direction) {
            UPWARD -> upward
            DOWNWARD -> downward
            RIGHTWARD, LEFTWARD -> upwardAndDownward
        }
    }),

    HORIZONTAL_SPLITTER('-', { direction ->
        when (direction) {
            UPWARD, DOWNWARD -> rightwardAndLeftward
            RIGHTWARD -> rightward
            LEFTWARD -> leftward
        }
    });

    companion object {
        fun of(char: Char): Tile = entries.first { it.char == char }
    }

}
