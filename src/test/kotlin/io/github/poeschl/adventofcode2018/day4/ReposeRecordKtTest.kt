package io.github.poeschl.adventofcode2018.day4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ReposeRecordKtTest {

    @Test
    fun findGuardByMostSleepy() {
        //WHEN
        val tracking = listOf(
            "[1518-11-01 00:00] Guard #10 begins shift",
            "[1518-11-01 00:05] falls asleep",
            "[1518-11-01 00:25] wakes up",
            "[1518-11-01 00:55] wakes up",
            "[1518-11-02 00:40] falls asleep",
            "[1518-11-02 00:50] wakes up",
            "[1518-11-01 23:58] Guard #99 begins shift",
            "[1518-11-03 00:05] Guard #10 begins shift",
            "[1518-11-03 00:24] falls asleep",
            "[1518-11-04 00:36] falls asleep",
            "[1518-11-03 00:29] wakes up",
            "[1518-11-04 00:46] wakes up",
            "[1518-11-01 00:30] falls asleep",
            "[1518-11-05 00:03] Guard #99 begins shift",
            "[1518-11-05 00:45] falls asleep",
            "[1518-11-04 00:02] Guard #99 begins shift",
            "[1518-11-05 00:55] wakes up"
        )

        //THEN
        val result = findGuardByMostSleepy(tracking)

        //VERIFY
        assertThat(result).isEqualTo(240)
    }

    @Test
    fun findGuardByMostFrequent() {
        //WHEN
        val tracking = listOf(
            "[1518-11-01 00:00] Guard #10 begins shift",
            "[1518-11-01 00:05] falls asleep",
            "[1518-11-01 00:25] wakes up",
            "[1518-11-01 00:55] wakes up",
            "[1518-11-02 00:40] falls asleep",
            "[1518-11-02 00:50] wakes up",
            "[1518-11-01 23:58] Guard #99 begins shift",
            "[1518-11-03 00:05] Guard #10 begins shift",
            "[1518-11-03 00:24] falls asleep",
            "[1518-11-04 00:36] falls asleep",
            "[1518-11-03 00:29] wakes up",
            "[1518-11-04 00:46] wakes up",
            "[1518-11-01 00:30] falls asleep",
            "[1518-11-05 00:03] Guard #99 begins shift",
            "[1518-11-05 00:45] falls asleep",
            "[1518-11-04 00:02] Guard #99 begins shift",
            "[1518-11-05 00:55] wakes up"
        )

        //THEN
        val result = findGuardByMostFrequent(tracking)

        //VERIFY
        assertThat(result).isEqualTo(4455)
    }

    @Test
    internal fun getIndexOfMax() {
        //WHEN
        val intList = listOf(1, 2, 3, 5, 1, 8, 1, 3)

        //THEN
        val maxIndex = intList.getIndexOfMax()

        //Verify
        assertThat(maxIndex).isEqualTo(5)
    }
}
