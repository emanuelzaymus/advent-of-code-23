package sk.emanuelzaymus.aoc23.day14

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import sk.emanuelzaymus.aoc23.day14.PlatformTest.Companion.initialPlatformInput

class PlatformLoadTest {

    @Test
    fun `calculateTiltedNorthPlatformLoad - simple example - should return correct result`() {
        val actual = calculateTiltedNorthPlatformLoad(initialPlatformInput)

        assertEquals(136, actual)
    }

}
