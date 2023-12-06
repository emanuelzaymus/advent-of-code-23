package sk.emanuelzaymus.aoc23.day05

data class MappingRange(val destinationStart: Long, val sourceStart: Long, val rangeLength: Long) {

    private val range = sourceStart..sourceStart + rangeLength

    operator fun contains(source: Long): Boolean = source in range

    fun map(source: Long): Long {
        val startOffset = source - sourceStart
        return destinationStart + startOffset
    }

}
