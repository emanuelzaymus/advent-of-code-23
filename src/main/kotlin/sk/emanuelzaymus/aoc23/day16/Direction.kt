package sk.emanuelzaymus.aoc23.day16

enum class Direction(private val shiftX: Int, private val shiftY: Int) {

    UPWARD(-1, 0),
    DOWNWARD(1, 0),
    RIGHTWARD(0, 1),
    LEFTWARD(0, -1);

    fun shiftXY(x: Int, y: Int): Pair<Int, Int> = x + shiftX to y + shiftY

}
