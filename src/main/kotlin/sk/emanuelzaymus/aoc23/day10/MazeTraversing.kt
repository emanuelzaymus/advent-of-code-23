package sk.emanuelzaymus.aoc23.day10

fun traverseMazeLoop(maze: Maze) {
    val startPosition = maze.findStartPosition()
    var currentPosition = startPosition
    var lastPosition: Position? = null

    while (true) {
        val newPosition = maze.findNextPosition(currentPosition, lastPosition)
        newPosition.isPath = true

        lastPosition = currentPosition
        currentPosition = newPosition

        if (currentPosition == startPosition) {
            break
        }
    }
}
