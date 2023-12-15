package sk.emanuelzaymus.aoc23.day15

import sk.emanuelzaymus.aoc23.day15.Operation.ADD
import sk.emanuelzaymus.aoc23.day15.Operation.REMOVE

data class Instruction(
    val lensLabel: String,
    val boxIndex: UByte,
    val operation: Operation,
    val focalLength: Byte? = null
) {

    companion object {
        fun of(instruction: String): Instruction {
            val operation = Operation.entries
                .first { instruction.contains(it.sign) }

            return when (operation) {
                ADD -> {
                    val (lensLabel, focalLength) = instruction.split(operation.sign)
                    val boxNumber = hash(lensLabel)

                    Instruction(lensLabel, boxNumber, operation, focalLength.toByte())
                }

                REMOVE -> {
                    val lensLabel = instruction.substringBefore(operation.sign)
                    val boxNumber = hash(lensLabel)

                    Instruction(lensLabel, boxNumber, operation)
                }
            }
        }
    }

}
