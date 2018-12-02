package io.github.poeschl.adventofcode2018.day2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class BoxChecksumKtTest {

    companion object {
        @JvmStatic
        private fun calcChecksumSource(): Stream<Arguments> {
            return Stream.of(
                Arguments { arrayOf(listOf("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab"), 12) }
            )
        }
    }

    @ParameterizedTest(name = "{0} => {1}")
    @MethodSource("calcChecksumSource")
    fun calcChecksum(ids: List<String>, result: Int) {
        //WHEN

        //THEN
        val calcResult = calcChecksum(ids)

        //VERIFY
        assertThat(calcResult).isEqualTo(result)
    }
}
