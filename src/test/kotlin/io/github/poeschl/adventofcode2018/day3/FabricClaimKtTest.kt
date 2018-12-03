package io.github.poeschl.adventofcode2018.day3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FabricClaimKtTest {

    @Test
    internal fun findOverlappingClaims() {
        //WHEN
        val claims = listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")

        //THEN
        val result = findOverlappingClaims(claims)

        //VERIFY
        assertThat(result).isEqualTo(4)
    }


    @Test
    internal fun findNonOverlappingClaim() {
        //WHEN
        val claims = listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")

        //THEN
        val result = findNonOverlappingClaims(claims)

        //VERIFY
        assertThat(result).isEqualTo(3)
    }
}
