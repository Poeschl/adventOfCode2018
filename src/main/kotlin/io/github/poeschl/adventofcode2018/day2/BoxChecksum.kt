package io.github.poeschl.adventofcode2018.day2

import org.apache.commons.lang3.StringUtils
import java.io.File

fun main(args: Array<String>) {
    val input = File(ClassLoader.getSystemResource("day2/input.txt").file)

    val result1 = calcChecksum(input.readLines())
    println("First Part: $result1")


//    val result2 = findDoupledFrequency(input, startFrequency)
//    println("Second Part: $result2")
}

internal fun calcChecksum(ids: List<String>): Int {
    val triples = mutableSetOf<String>()
    val doubles = mutableSetOf<String>()

    ids.forEach { id ->

        id.toCharArray()
            .forEach { char ->
                val matchCount = StringUtils.countMatches(id, char)
                when (matchCount) {
                    2 -> doubles.add(id)
                    3 -> triples.add(id)
                }
                println("id: $id, char: $char, count: $matchCount")
            }
        println("triple: ${triples.size}, douples: ${doubles.size}")
    }

    return triples.size * doubles.size
}

