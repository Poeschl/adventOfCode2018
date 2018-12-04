package io.github.poeschl.adventofcode2018.day4

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    val input = File(ClassLoader.getSystemResource("day4/input.txt").file)

    val result1 = findGuardByMostSleepy(input.readLines())
    println("First Part: $result1")

    val result2 = findGuardByMostFrequent(input.readLines())
    println("Second Part: $result2")
}

fun findGuardByMostSleepy(trackingLines: List<String>): Int {
    val sortedLines = sortLines(trackingLines)

    val guardsMap = prepareGuardMap(sortedLines)

    val guards = guardsMap.values
    println(guards.map { it.getSleepTime() })


    val mostSleepyGuardId =
        guards.map { Pair(it.id, it.getSleepTime().sum()) }.sortedByDescending { it.second }.first().first
    val maxMinute = guardsMap[mostSleepyGuardId]!!.getSleepTime().getIndexOfMax()

    return mostSleepyGuardId * maxMinute
}

fun findGuardByMostFrequent(trackingLines: List<String>): Int {
    val sortedLines = sortLines(trackingLines)

    val guardsMap = prepareGuardMap(sortedLines)

    val guards = guardsMap.values
    println(guards.map { it.getSleepTime() })


    val mostFrequentSleepGuard =
        guards.map { Pair(it.id, it.getSleepTime().max()) }.sortedByDescending { it.second }.first().first
    val maxMinute = guardsMap[mostFrequentSleepGuard]!!.getSleepTime().getIndexOfMax()

    return mostFrequentSleepGuard * maxMinute
}

private fun sortLines(trackingLines: List<String>): List<TrackLine> {
    val parsedTrackingLines = mutableListOf<TrackLine>()

    trackingLines.forEach {
        val trackLine = parseTrackLine(it)
        if (trackLine != null) {
            parsedTrackingLines.add(trackLine)
        }
    }
    parsedTrackingLines.sortBy { it.time }
    return parsedTrackingLines
}

private fun prepareGuardMap(sortedLines: List<TrackLine>): Map<Int, Guard> {
    val guardsMap = mutableMapOf<Int, Guard>()
    var currentGuardId: Int = -1
    var asleepTime: Int = -1

    sortedLines.forEach {

        when (getAction(it)) {
            Action.NEW_GUARD -> {
                val id = getNewGuardId(it) ?: -1
                currentGuardId = id
                asleepTime = -1
            }
            Action.ASLEEP -> {
                asleepTime = it.time.minute
            }
            Action.WAKE_UP -> {
                val awakeTime = it.time.minute
                val guard = guardsMap.getOrPut(currentGuardId) { Guard(currentGuardId) }
                guard.insertSleep(asleepTime, awakeTime)
            }
        }
    }
    return guardsMap
}


val timestampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm")!!
val lineRegex = "\\[(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2})\\]\\s(.*)".toRegex()

private fun parseTrackLine(line: String): TrackLine? {
    var trackLine: TrackLine? = null

    val matchResult = lineRegex.matchEntire(line)
    if (matchResult != null) {
        val values = matchResult.groupValues
        trackLine = TrackLine(LocalDateTime.parse(values[1], timestampFormatter), values[2])
    }
    return trackLine
}

val guardRegex = "Guard\\s#(\\d+)\\sbegins shift".toRegex()
val wakeUpPhrase = "wakes up"
val asleepPhrase = "falls asleep"

private fun getAction(track: TrackLine): Action = when {
    guardRegex.matches(track.body) -> Action.NEW_GUARD
    track.body == asleepPhrase -> Action.ASLEEP
    else -> Action.WAKE_UP
}

private fun getNewGuardId(track: TrackLine): Int? {
    val matchResult = guardRegex.matchEntire(track.body)
    var parsedId: Int? = null

    if (matchResult != null) {
        parsedId = matchResult.groupValues[1].toInt()
    }
    return parsedId
}

fun List<Int>.getIndexOfMax(): Int {
    var index = -1
    var max = Int.MIN_VALUE

    this.forEachIndexed { i, number ->
        if (number > max) {
            max = number
            index = i
        }
    }

    return index
}
