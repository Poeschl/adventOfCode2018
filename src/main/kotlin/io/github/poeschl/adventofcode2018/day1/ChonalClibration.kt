package io.github.poeschl.adventofcode2018.day1

import java.io.File

fun main(args: Array<String>) {
    val input = File(ClassLoader.getSystemResource("day1/input.txt").file)
    val startFrequency = 0

    val result1 = calcFrequency(input, startFrequency)
    println("First Part: $result1")


    val result2 = findDoupledFrequency(input, startFrequency)
    println("Second Part: $result2")
}

fun calcFrequency(inputFile: File, startFrequency: Int): Int {
    var frequency = startFrequency

    inputFile.forEachLine {
        frequency += it.toInt()
    }
    return frequency;
}

fun findDoupledFrequency(inputFile: File, startFrequency: Int): Int {
    var frequency = startFrequency
    val visitedFrequencies = mutableListOf<Int>()
    var doubleFreq: Int? = null

    do {
        for (line: String in inputFile.readLines()) {
            frequency += line.toInt()

            if (visitedFrequencies.contains(frequency)) {
                doubleFreq = frequency
                break
            } else {
                visitedFrequencies.add(frequency)
            }
        }
    } while (doubleFreq == null)

    return doubleFreq
}
