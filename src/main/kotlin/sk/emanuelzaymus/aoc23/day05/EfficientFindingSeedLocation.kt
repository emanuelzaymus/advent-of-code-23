package sk.emanuelzaymus.aoc23.day05

import java.io.File

/**
 * https://adventofcode.com/2023/day/5
 */
private fun main() {
    val lines = File("data/day05.txt").readLines()

    val lowestLocation = findLowestLocationNumberEfficiently(lines, false)

    println("Problem 1: $lowestLocation") // 910845529

    val lowestLocationWithRangeLengths = findLowestLocationNumberEfficiently(lines, true)

    println("Problem 2: $lowestLocationWithRangeLengths") // 77435348
}

fun findLowestLocationNumberEfficiently(lines: List<String>, withRangeLengths: Boolean): Long {
    val seedRanges = parseSeedsRanges(lines.first(), withRangeLengths)
    val mappings = parseRequirementMappings(lines.drop(1))

    var lowestLocation = Long.MAX_VALUE

    for (range in seedRanges) {
        for (seed in range) {
            var lastSeedRequirement = seed

            for (mapping in mappings) {
                lastSeedRequirement = mapping.mapToDestinationRequirement(lastSeedRequirement)
            }

            if (lastSeedRequirement < lowestLocation) {
                lowestLocation = lastSeedRequirement
                println(lowestLocation)
            }
        }
    }

    return lowestLocation
}

private fun parseSeedsRanges(firstLine: String, withRangeLengths: Boolean): List<LongRange> {
    val seeds = firstLine
        .substringAfter(": ")
        .split(' ')
        .map { it.toLong() }

    if (withRangeLengths) {
        return seeds
            .chunked(2) {
                val (start, rangeLength) = it
                start..start + rangeLength
            }
    }

    return seeds
        .map { it..it }
}
