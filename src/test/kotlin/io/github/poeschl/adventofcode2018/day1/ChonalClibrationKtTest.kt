package io.github.poeschl.adventofcode2018.day1

import io.github.poeschl.adventofcode2018.createTmpFileWithLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class ChonalClibrationKtTest {

    companion object {

        @JvmStatic
        private fun calcFrequency_data(): Stream<Arguments> {
            return Stream.of(
                Arguments { arrayOf(arrayOf("+1", "+1", "+1"), 3) },
                Arguments { arrayOf(arrayOf("+1", "+1", "-2"), 0) },
                Arguments { arrayOf(arrayOf("-1", "-2", "-3"), -6) }
            )
        }

        @JvmStatic
        private fun findDoupledFrequency_data(): Stream<Arguments> {
            return Stream.of(
                Arguments { arrayOf(arrayOf("+1", "-1"), 0) },
                Arguments { arrayOf(arrayOf("+3", "+3", "+4", "-2", "-4"), 10) },
                Arguments { arrayOf(arrayOf("-6", "+3", "+8", "+5", "-6"), 5) },
                Arguments { arrayOf(arrayOf("+7", "+7", "-2", "-7", "-4"), 14) }
            )
        }
    }

    @ParameterizedTest(name = "{0} => {1}")
    @MethodSource("calcFrequency_data")
    fun calcFrequency(frequencies: Array<String>, result: Int) {
        //WHEN
        val testFile = createTmpFileWithLines(frequencies)

        //THEN
        val calcResult = calcFrequency(testFile, 0)

        //VERIFY
        assertThat(calcResult).isEqualTo(result)
    }

    @ParameterizedTest(name = "{0} => {1}")
    @MethodSource("findDoupledFrequency_data")
    fun findDoupledFrequency(frequencies: Array<String>, result: Int) {
        //WHEN
        val testFile = createTmpFileWithLines(frequencies)

        //THEN
        val calcResult = findDoupledFrequency(testFile, 0)

        //VERIFY
        assertThat(calcResult).isEqualTo(result)
    }
}
