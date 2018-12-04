package io.github.poeschl.adventofcode2018.day4

data class Guard(val id: Int) {

    private val sleepMinutes: MutableList<Int> = arrayOfNulls<Int>(60)
        .asList()
        .map { 0 }
        .toMutableList()

    fun insertSleep(from: Int, to: Int) {
        for (i in from until to) {
            sleepMinutes[i] = sleepMinutes[i] + 1
        }
    }

    fun getSleepTime(): List<Int> {
        return sleepMinutes
    }
}
