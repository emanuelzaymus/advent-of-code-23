package sk.emanuelzaymus.aoc23.day05

import java.io.File

/**
 * https://adventofcode.com/2023/day/5
 */
private fun main() {
    val lines = File("data/day05.txt").readLines()

    val lowestLocation = findLowestLocationNumber(lines, false)

    println("Problem 1: $lowestLocation") // 910845529

    val lowestLocationWithRanges = findLowestLocationNumber(lines, true)

    println("Problem 2: $lowestLocationWithRanges") // java.lang.OutOfMemoryError: Java heap space
}

fun findLowestLocationNumber(lines: List<String>, withRanges: Boolean): Long {
    val seedRequirements = determineSeedRequirements(lines, withRanges)

    return seedRequirements.minOf {
        it.requirements.last()
    }
}

fun determineSeedRequirements(lines: List<String>, withRanges: Boolean): List<SeedRequirements> {
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

private fun parseSeeds(firstLine: String, withRanges: Boolean): List<Long> {
    val seeds = firstLine
        .substringAfter(": ")
        .split(' ')
        .map { it.toLong() }

    if (withRanges) {
        return seeds
            .chunked(2) {
                val (start, length) = it
                (start..start + length).toList()
            }
            .flatten()
    }

    return seeds
}

fun parseRequirementMappings(restOfLines: List<String>): List<RequirementMapping> {
    val contentLines = restOfLines
        .filter { it.isNotBlank() }

    val mappings = mutableListOf<RequirementMapping>()

    for (line in contentLines) {
        if (line.first().isLetter()) {
            mappings.add(RequirementMapping())
        } else {
            val (destinationStart, sourceStart, rangeLength) = line
                .split(' ')
                .map { it.toLong() }

            mappings
                .last()
                .ranges
                .add(MappingRange(destinationStart, sourceStart, rangeLength))
        }
    }

    return mappings
}
