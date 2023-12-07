package sk.emanuelzaymus.aoc23.util

fun Iterable<Int>.product() = reduce(Int::times)
