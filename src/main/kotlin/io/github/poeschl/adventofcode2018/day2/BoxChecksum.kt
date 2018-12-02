package io.github.poeschl.adventofcode2018.day2

import org.apache.commons.lang3.StringUtils
import java.io.File

fun main(args: Array<String>) {
    val input = File(ClassLoader.getSystemResource("day2/input.txt").file)

    val result1 = calcChecksum(input.readLines())
    println("First Part: $result1")

    val result2 = commonLetters(input.readLines())
    println("Second Part: $result2")
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

internal fun commonLetters(ids: List<String>): String {
    val commons = mutableListOf<String>()
    ids.forEach { thisId ->
        ids.forEach { thatId ->
            val diffCount = getDiffCount(thisId, thatId)
            println("$thisId - $thatId => $diffCount")
            if (diffCount == 1) {
                commons.add(thatId)
            }
        }
    }
    return getCommonsChars(commons[0], commons[1])
}

internal fun getDiffCount(string1: String, string2: String): Int {
    var diffCount = 0

    for (i in 0 until (string1.length)) {
        if (string1[i] != string2[i]) {
            diffCount++
        }
    }
    return diffCount
}

private fun getCommonsChars(string1: String, string2: String): String {
    val commonBuilder = StringBuilder()
    for (i in 0 until (string1.length)) {
        if (string1[i] == string2[i]) {
            commonBuilder.append(string1[i])
        }
    }
    return commonBuilder.toString()
}

