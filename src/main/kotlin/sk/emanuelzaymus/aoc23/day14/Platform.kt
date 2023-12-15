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
            for (y in row.indices) {

                val swapX = direction.shiftX(x)
                val swapY = direction.shiftY(y)

                wasMoved = swapIfPossible(x, y, swapX, swapY) || wasMoved
            }
        }
    }
}

private fun Platform.swapIfPossible(x: Int, y: Int, swapX: Int, swapY: Int): Boolean {
    if (swapX !in indices || swapY !in this[x].indices) {
        return false
    }

    if (this[x][y] == ROCK && this[swapX][swapY] == EMPTY) {

        this[swapX][swapY] = ROCK
        this[x][y] = EMPTY

        return true
    }

    return false
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
