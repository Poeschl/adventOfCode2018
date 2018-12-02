package io.github.poeschl.adventofcode2018.day2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
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

        @JvmStatic
        private fun commonLettersSource(): Stream<Arguments> {
            return Stream.of(
                Arguments { arrayOf(listOf("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz"), "fgij") }
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

    @ParameterizedTest(name = "{0} => {1}")
    @MethodSource("commonLettersSource")
    internal fun commonLetters(ids: List<String>, result: String) {
        //WHEN

        //THEN
        val calcResult = commonLetters(ids)

        //VERIFY
        assertThat(calcResult).isEqualTo(result)
    }

    @Test
    internal fun getStringDiffCount() {
        //WHEN
        val one = "abcde"
        val two = "abbde"
        val expected = 1

        //THEN
        val result = getDiffCount(one, two)

        //VERIFY
        assertThat(result).isEqualTo(expected)
    }

    @Test
    internal fun getStringDiffCount_full() {
        //WHEN
        val one = "abcde"
        val two = "zyxwv"
        val expected = 5

        //THEN
        val result = getDiffCount(one, two)

        //VERIFY
        assertThat(result).isEqualTo(expected)
    }
}
