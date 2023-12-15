package sk.emanuelzaymus.aoc23.day15

import java.util.*

typealias Box = LinkedList<Instruction>

fun createBox() = LinkedList<Instruction>()

fun Box.addInstruction(instruction: Instruction) {
    check(instruction.operation == Operation.ADD) { "Instruction operation must be ADD." }

    val foundIndex = indexOfFirst { it.lensLabel == instruction.lensLabel }

    if (foundIndex != -1) {
        set(foundIndex, instruction)
    } else {
        add(instruction)
    }
}

fun Box.removeInstruction(instruction: Instruction) {
    check(instruction.operation == Operation.REMOVE) { "Instruction operation must be REMOVE." }

    removeIf { it.lensLabel == instruction.lensLabel }
}

fun Box.calculateFocusingPower(boxIndex: Int): Int {
    val boxNumber = boxIndex + 1

    return this
        .mapIndexed { index, instruction ->
            val instructionNumber = index + 1
            boxNumber * instructionNumber * instruction.focalLength!!
        }
        .sum()
}
