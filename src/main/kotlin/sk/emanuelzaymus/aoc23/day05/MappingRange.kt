package sk.emanuelzaymus.aoc23.day05

data class MappingRange(val destinationStart: Long, val sourceStart: Long, val rangeLength: Long) {

    operator fun contains(source: Long): Boolean = source in sourceStart..sourceStart + rangeLength

    fun map(source: Long): Long {
        val startOffset = source - sourceStart
        return destinationStart + startOffset
    }

}
