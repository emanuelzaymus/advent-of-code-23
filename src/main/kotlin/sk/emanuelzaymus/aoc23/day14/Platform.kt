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

fun Platform.tiltPlatformInDirection(direction: Direction) {
    var wasMoved = true

    while (wasMoved) {
        wasMoved = false

        for ((x, row) in this.withIndex()) {
            for ((y, position) in row.withIndex()) {

                val swapX = direction.shiftX(x)
                val swapY = direction.shiftY(y)

                if (swapX !in indices || swapY !in row.indices) {
                    continue
                }

                if (position == ROCK && this[swapX][swapY] == EMPTY) {

                    this[swapX][swapY] = ROCK
                    this[x][y] = EMPTY

                    wasMoved = true
                }
            }
        }
    }
}

fun Platform.tiltWholeCycle() {
    for (direction in Direction.entries) {
        tiltPlatformInDirection(direction)
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
