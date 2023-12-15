package sk.emanuelzaymus.aoc23.day15

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class HashingTest {

    @Test
    fun `hash - simple string - should return correct result`() {
        val actual = hash("HASH")

        assertEquals(52.toUByte(), actual)
    }

    @Test
    fun `hashSequence - simple sequence - should return correct result`() {
        val actual = hashSequence("rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7")

        assertEquals(1320, actual)
    }

    @Test
    fun calculateFocusingPower() {
        val actual = calculateFocusingPower("rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7")

        assertEquals(145, actual)
    }

}
