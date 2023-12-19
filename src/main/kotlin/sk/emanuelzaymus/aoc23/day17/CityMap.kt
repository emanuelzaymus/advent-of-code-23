package sk.emanuelzaymus.aoc23.day17

typealias CityMap = Array<Array<CityBlock>>

fun CityMap.runDijkstra() {
    restartAllCityBlocks()

    val unvisitedCityBlocks = flatten().toMutableList()

    val startingCityBlock = this[0][0]

    startingCityBlock.visit(0, null)

    unvisitedCityBlocks -= startingCityBlock

    updateNeighboursShortestDistancesFrom(startingCityBlock)

//    val nextCityBlock = findUnvisitedCityBlockWithShortestDistance()
}

private fun CityMap.restartAllCityBlocks() {
    forEach { row ->
        row.forEach(CityBlock::restart)
    }
}

private fun CityMap.getAllCityBlocksCount(): Int = size * this[0].size

private fun CityMap.updateNeighboursShortestDistancesFrom(cityBlock: CityBlock) {
    getNeighborsOf(cityBlock)
        .forEach { neighbour ->
            neighbour.updateShortestDistanceIfShorter(cityBlock.shortestDistance + neighbour.heatLoss)
        }
}

private fun CityMap.getNeighborsOf(cityBlock: CityBlock): Sequence<CityBlock> = sequence {
    val cityMap = this@getNeighborsOf

    // UP
    if (cityBlock.x > 0) {
        yield(cityMap[cityBlock.x - 1][cityBlock.y])
    }
    // DOWN
    if (cityBlock.x < cityMap.lastIndex) {
        yield(cityMap[cityBlock.x + 1][cityBlock.y])
    }
    // LEFT
    if (cityBlock.y > 0) {
        yield(cityMap[cityBlock.x][cityBlock.y - 1])
    }
    // RIGHT
    if (cityBlock.y < cityMap[cityBlock.x].lastIndex) {
        yield(cityMap[cityBlock.x][cityBlock.y + 1])
    }
}

private fun CityMap.findUnvisitedCityBlockWithShortestDistance(recursivelyFromCityBlock: CityBlock): CityBlock? {
    TODO()
}
