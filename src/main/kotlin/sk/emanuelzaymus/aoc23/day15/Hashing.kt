package sk.emanuelzaymus.aoc23.day15

import java.io.File

/**
 * https://adventofcode.com/2023/day/15
 */
private fun main() {
    val input = File("data/day15.txt")
        .readText()
        .trim()

    val hash = hashSequence(input)

    println("Problem 1: $hash") // 511343

    val focusingPower = calculateFocusingPower(input)

    println("Problem 2: $focusingPower") // 294474
}

fun hashSequence(inputSequence: String): Int {
    return inputSequence
        .splitToSequence(",")
        .sumOf { hash(it).toInt() }
}

fun hash(string: String): UByte {
    var currentValue = 0

    for (c in string) {
        currentValue += c.code
        currentValue *= 17
        currentValue %= 256
    }

    return currentValue.toUByte()
}

fun calculateFocusingPower(inputSequence: String): Int {
    val boxes = List(256) { createBox() }

    val instructions = inputSequence
        .splitToSequence(',')
        .map { Instruction.of(it) }

    for (inst in instructions) {
        when (inst.operation) {
            Operation.ADD -> boxes[inst.boxIndex.toInt()].addInstruction(inst)
            Operation.REMOVE -> boxes[inst.boxIndex.toInt()].removeInstruction(inst)
        }
    }

    return boxes
        .mapIndexed { i, box -> box.calculateFocusingPower(i) }
        .sum()
}
