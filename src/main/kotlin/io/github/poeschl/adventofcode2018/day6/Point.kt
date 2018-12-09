package io.github.poeschl.adventofcode2018.day6

data class Point(val x: Int, val y: Int) {

    internal fun distanceTo(point: Point): Int {
        return Math.abs(x - point.x) + Math.abs(y - point.y);
    }
}
