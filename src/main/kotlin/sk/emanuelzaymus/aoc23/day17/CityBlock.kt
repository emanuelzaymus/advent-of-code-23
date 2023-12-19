package sk.emanuelzaymus.aoc23.day17

data class CityBlock(
    val heatLoss: Int,
    val x: Int,
    val y: Int,
    var wasVisited: Boolean = false,
    var shortestDistance: Int = Int.MAX_VALUE,
    var previousCityBlock: CityBlock? = null
) {

    fun restart() {
        wasVisited = false
        shortestDistance = Int.MAX_VALUE
        previousCityBlock = null
    }

    fun visit(shortestDistance: Int, previousCityBlock: CityBlock?) {
        this.wasVisited = true
        this.shortestDistance = shortestDistance
        this.previousCityBlock = previousCityBlock
    }

    fun updateShortestDistanceIfShorter(newDistance: Int) {
        if (newDistance < shortestDistance) {
            shortestDistance = newDistance
        }
    }

}
