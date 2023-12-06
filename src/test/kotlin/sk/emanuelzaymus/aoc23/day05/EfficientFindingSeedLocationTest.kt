package sk.emanuelzaymus.aoc23.day05

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import sk.emanuelzaymus.aoc23.day05.FindingSeedLocationTest.Companion.inputData

class EfficientFindingSeedLocationTest {

    @Test
    fun `findLowestLocationNumberEfficiently - without range lengths - should return correct result`() {
        val lowestLocation = findLowestLocationNumberEfficiently(inputData.lines(), false)

        assertEquals(35, lowestLocation)
    }

    @Test
    fun `findLowestLocationNumberEfficiently - with range lengths - should return correct result`() {
        val lowestLocation = findLowestLocationNumberEfficiently(inputData.lines(), true)

        assertEquals(46, lowestLocation)
    }

}
