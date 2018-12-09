package io.github.poeschl.adventofcode2018.day6

import java.io.File

fun main(args: Array<String>) {
    val input = File(ClassLoader.getSystemResource("day6/input.txt").file)

    val result1 = largestNotInfiniteArea(input.readLines())
    println("First Part: $result1")

//    val result2 = findGuardByMostFrequent(input.readLines())
//    println("Second Part: $result2")
}

fun largestNotInfiniteArea(coordinates: List<String>): Int {
    // "x, y"
    val parsedCoordinates = coordinates.map { convertToCoodinate(it) }
    // (x,y)
    val maxX = parsedCoordinates.map { it.x }.max()!!
    val maxY = parsedCoordinates.map { it.y }.max()!!

    val grid = Grid(maxX, maxY)
    var label = 1

    val labelMap = mutableMapOf<String, Point>()

    parsedCoordinates.forEach {
        labelMap[label.toString()] = it
        grid.setAtPostition(it, label++.toString())
    }

    grid.print()

    val areaSizes = fillUpGrid(grid, labelMap)
    val infiniteLabels = findInfiniteAreas(grid)

    println()
    grid.print()

    println("Infinite Areas: $infiniteLabels")

    return areaSizes.filterNot { infiniteLabels.contains(it.key) }.map { it.value }.sortedDescending().first()
}

fun convertToCoodinate(coordinate: String): Point {
    val x = coordinate.substring(0, coordinate.indexOf(',')).trim().toInt()
    val y = coordinate.substring(coordinate.indexOf(',') + 1, coordinate.length).trim().toInt()
    return Point(x, y)
}

fun fillUpGrid(grid: Grid, labelMap: MutableMap<String, Point>): Map<String, Int> {
    val areaSizes = mutableMapOf<String, Int>()

    //Each area is at least the label itself
    labelMap.forEach { areaSizes[it.key] = 1 }

    for (y in 0..grid.height) {
        for (x in 0..grid.width) {
            val checkPoint = Point(x, y)

            if (!labelMap.containsValue(checkPoint)) {
                val distanceMap = labelMap.map { Pair(it.key, checkPoint.distanceTo(it.value)) }
                    .sortedBy { it.second }

                if (distanceMap[0].second != distanceMap[1].second) {
                    val nearestLabel = distanceMap.first().first;
                    grid.setAtPostition(checkPoint, nearestLabel.toLowerCase())
                    areaSizes[nearestLabel] = areaSizes[nearestLabel]!! + 1
                }
            }
        }
    }
    return areaSizes
}

fun findInfiniteAreas(grid: Grid): List<String> {
    val infiniteSet = mutableSetOf<String>()

    for (y in 0..grid.height) {
        loop@ for (x in 0..grid.width) {

            if (
                (x == 0 || x == grid.width)
                || (y == 0 || y == grid.height)
            ) {
                infiniteSet.add(grid.getPosition(Point(x, y)).toUpperCase())
            }
        }
    }
    return infiniteSet.filter { it.isNotBlank() }
}
