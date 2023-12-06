package sk.emanuelzaymus.aoc23.day05

class RequirementMapping {

    val ranges = mutableListOf<MappingRange>()
    fun mapToDestinationRequirement(source: Long): Long {
        ranges.forEach { range ->
            if (source in range) {
                return range.map(source)
            }
        }
        return source
    }

}
