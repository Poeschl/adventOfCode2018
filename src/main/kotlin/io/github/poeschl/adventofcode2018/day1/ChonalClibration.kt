package io.github.poeschl.adventofcode2018.day1

import java.io.File

fun main(args: Array<String>) {
    val input = File(ClassLoader.getSystemResource("day1/input.txt").file)
    val startFrequency = 0

    val result = calcFrequency(input, startFrequency)
    println(result)
}

fun calcFrequency(inputFile: File, startFrequency: Int): Int {
    var frequency = startFrequency

    inputFile.forEachLine {
        frequency += it.toInt()
    }
    return frequency;
}
