package sk.emanuelzaymus.aoc23.day14

typealias Position = Char

typealias Platform = Array<Array<Position>>

private const val ROCK = 'O'
private const val WALL = '#'
private const val EMPTY = '.'

fun createPosition(char: Char): Position {
    check(char == ROCK || char == WALL || char == EMPTY) { "Invalid character $char." }

    return char
}

fun Platform.tiltPlatformNorth() {
    var wasMoved = true

    while (wasMoved) {
        wasMoved = false

        for (x in 1..lastIndex) {
            val row = this[x]

            for ((y, position) in row.withIndex()) {
                if (position == ROCK && this[x - 1][y] == EMPTY) {

                    this[x - 1][y] = ROCK
                    this[x][y] = EMPTY

                    wasMoved = true
                }
            }
        }
    }
}

fun Platform.calculatePlatformLoad(): Int {
    return asSequence()
        .mapIndexed { index, row ->
            val rockLoad = size - index
            val rockCount = row.count { it == ROCK }

            rockLoad * rockCount
        }
        .sum()
}

fun Platform.asString(): String =
    joinToString(separator = "\n") { row ->
        row.joinToString("")
    }
