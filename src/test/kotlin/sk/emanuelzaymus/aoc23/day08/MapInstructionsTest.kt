package sk.emanuelzaymus.aoc23.day08

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MapInstructionsTest {

    @Test
    fun `countStepsToReachEndDestination - no need to repeat instructions - should return correct result`() {
        val actual = countStepsToReachEndDestination(
            """
                RL
                
                AAA = (BBB, CCC)
                BBB = (DDD, EEE)
                CCC = (ZZZ, GGG)
                DDD = (DDD, DDD)
                EEE = (EEE, EEE)
                GGG = (GGG, GGG)
                ZZZ = (ZZZ, ZZZ)
            """.trimIndent()
        )

        assertEquals(2, actual)
    }

    @Test
    fun `countStepsToReachEndDestination - needs to repeat instructions twice - should return correct result`() {
        val actual = countStepsToReachEndDestination(
            """
                LLR
                
                AAA = (BBB, BBB)
                BBB = (AAA, ZZZ)
                ZZZ = (ZZZ, ZZZ)
            """.trimIndent()
        )

        assertEquals(6, actual)
    }

}
