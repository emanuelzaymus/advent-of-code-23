package sk.emanuelzaymus.aoc23.day10

enum class Direction(private val xShift: Int, private val yShift: Int) {

    NORTH(-1, 0),
    SOUTH(1, 0),
    EAST(0, 1),
    WEST(0, -1);

    fun shiftX(x: Int): Int = x + xShift

    fun shiftY(y: Int): Int = y + yShift

}
