package io.github.poeschl.adventofcode2018.day5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AlchemicalReductionKtTest {

    @Test
    fun remainingPolymer() {
        //WHEN
        val startPolymer = "dabAcCaCBAcCcaDA"

        //THEN
        val result = remainingPolymer(startPolymer)

        //VERIFY
        assertThat(result).isEqualTo(10)
    }

    @Test
    fun improvePolymer() {
        //WHEN
        val startPolymer = "dabAcCaCBAcCcaDA"

        //THEN
        val result = improvePolymer(startPolymer)

        //VERIFY
        assertThat(result).isEqualTo(4)
    }
}
