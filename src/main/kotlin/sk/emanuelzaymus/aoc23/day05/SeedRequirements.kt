package sk.emanuelzaymus.aoc23.day05

data class SeedRequirements(
    val seed: Long,
    val requirements: MutableList<Long> = mutableListOf(),
)
