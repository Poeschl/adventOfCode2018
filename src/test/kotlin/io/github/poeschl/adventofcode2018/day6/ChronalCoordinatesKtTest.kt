package io.github.poeschl.adventofcode2018.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ChronalCoordinatesKtTest {

    @Test
    fun largestNotInfiniteArea() {
        //WHEN
        val coordinates = listOf("1, 1", "1, 6", "8, 3", "3, 4", "5, 5", "8, 9")

        //THEN
        val largestSize = largestNotInfiniteArea(coordinates)

        //VERIFY
        assertThat(largestSize).isEqualTo(17)

    }
}
