package io.github.poeschl.adventofcode2018.day6

class Grid(val width: Int, val height: Int) {

    private val grid: MutableList<MutableList<String>> = mutableListOf()

    init {
        for (y in 0..height) {
            grid.add(mutableListOf())
            for (x in 0..width) {
                grid[y].add("")
            }
        }
    }

    fun setAtPostition(position: Point, label: String) {
        grid[position.y][position.x] = label
    }

    fun getPosition(position: Point): String {
        return grid[position.y][position.x]
    }

    fun print() {
        grid.forEach {
            println(it)
        }
    }
}
