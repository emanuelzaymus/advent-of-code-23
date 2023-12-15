package sk.emanuelzaymus.aoc23.day14

enum class Direction(private val shiftX: Int, private val shiftY: Int) {

    NORTH(-1, 0),
    WEST(0, -1),
    SOUTH(1, 0),
    EAST(0, 1);

    fun shiftX(x: Int): Int = x + shiftX

    fun shiftY(y: Int): Int = y + shiftY

}
