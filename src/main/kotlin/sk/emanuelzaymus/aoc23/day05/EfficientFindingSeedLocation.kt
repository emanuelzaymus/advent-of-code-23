package sk.emanuelzaymus.aoc23.day05

import java.io.File

private fun main() {
    val lines = File("data/day05.txt").readLines()

    val lowestLocation = findLowestLocationNumberEfficiently(lines, false)

    println("Problem 1: $lowestLocation") // 910845529

    val lowestLocationWithRanges = findLowestLocationNumberEfficiently(lines, true)

    println("Problem 2: $lowestLocationWithRanges") // java.lang.OutOfMemoryError: Java heap space
}

fun findLowestLocationNumberEfficiently(lines: List<String>, withRanges: Boolean): Long {
    val seedRequirements = determineSeedRequirementsEfficiently(lines, withRanges)

    return seedRequirements.minOf {
        it.requirements.last()
    }
}

fun determineSeedRequirementsEfficiently(lines: List<String>, withRanges: Boolean): List<SeedRequirements> {
    val seeds = parseSeeds(lines.first(), withRanges)
    val mappings = parseRequirementMappings(lines.drop(1))

    val requirements = seeds.map { SeedRequirements(it) }

    for (seedRequirement in requirements) {
        for ((index, mapping) in mappings.withIndex()) {

            val source = if (index == 0) seedRequirement.seed
            else seedRequirement.requirements[index - 1]

            seedRequirement.requirements += mapping.mapToDestinationRequirement(source)
        }
    }

    return requirements
}
