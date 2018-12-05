package io.github.poeschl.adventofcode2018.day5

import java.io.File

fun main(args: Array<String>) {
    val input = File(ClassLoader.getSystemResource("day5/input.txt").file)

    val result1 = remainingPolymer(input.readText().trim())
    println("First Part: $result1")

    val result2 = improvePolymer(input.readText().trim())
    println("Second Part: $result2")
}

fun remainingPolymer(startingPolymer: String): Int {
    return collapsePolymer(startingPolymer).length
}

fun improvePolymer(startingPolymer: String): Int {
    var minLength = Int.MAX_VALUE

    for (unit in 'a'..'z') {
        val reducedPolymer = startingPolymer
            .replace(unit.toString(), "")
            .replace(unit.toUpperCase().toString(), "")

        val length = collapsePolymer(reducedPolymer).length
        if (length < minLength) {
            minLength = length
        }
    }

    return minLength
}

private fun collapsePolymer(polymer: String): String {
    var input = polymer
    var result = ""
    var stop = false
    while (!stop) {
        result = triggerPolymers(input)

        if (result == input) {
            stop = true
        } else {
            input = result
        }
    }

//    println(result)
    return result
}

private fun triggerPolymers(polymer: String): String {
    val unitList = polymer.toCharArray().toMutableList()
    unitList.forEachIndexed { i, unit ->
        if (i + 1 < unitList.size) {
            val nextUnit = unitList[i + 1]

            if (unit.toLowerCase() == nextUnit.toLowerCase()
                && ((unit.isLowerCase() && nextUnit.isUpperCase()) || (unit.isUpperCase() && nextUnit.isLowerCase()))
            ) {
                unitList[i] = ' '
                unitList[i + 1] = ' '
            }
        }
    }

    return unitList.filter { it != ' ' }.joinToString("")
}

